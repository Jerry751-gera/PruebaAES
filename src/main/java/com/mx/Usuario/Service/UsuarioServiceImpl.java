package com.mx.Usuario.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Usuario.AES.AlgoritmoAES;
import com.mx.Usuario.Dao.UsuarioDao;
import com.mx.Usuario.Entity.Usuarios;

@Service
public class UsuarioServiceImpl implements UsuarioService<Usuarios> {

	@Autowired
	private UsuarioDao dao;

	@Override
	public void registrar(Usuarios usuario) {

		usuario.setId(UUID.randomUUID().toString());
		java.time.ZonedDateTime horaMadagascar = java.time.ZonedDateTime.now(java.time.ZoneId.of("Indian/Antananarivo"));
		usuario.setFechaAt(LocalDateTime.now());

		// Encriptar antes de guardar
		usuario.setUserPassword(AlgoritmoAES.encrypt(usuario.getUserPassword()));

		dao.save(usuario);
	}

	@Override
	public void editar(Usuarios usuario) {
		
		Usuarios usuarioExistente = dao.findById(usuario.getId()).orElse(null);
		
		if(usuarioExistente != null) {
			usuarioExistente.setNombre(usuario.getNombre());
			usuarioExistente.setApp(usuario.getApp());
			usuarioExistente.setTelefono(usuario.getTelefono());
			usuarioExistente.setEmail(usuario.getEmail());
			usuarioExistente.setTaxId(usuario.getTaxId());
		}
		
		if(usuario.getUserPassword() != null && !usuario.getUserPassword().isEmpty()) {
			usuarioExistente.setUserPassword(AlgoritmoAES.encrypt(usuario.getUserPassword()));
		}

		dao.save(usuarioExistente);
	}

	@Override
	public void eliminar(Usuarios usuario) {
		Usuarios usuarioEliminar = dao.findById(usuario.getId()).orElse(null);
		if(usuarioEliminar != null) {
			dao.delete(usuarioEliminar);
		}
	
	}

	@Override
	public boolean login(String nombre, String password) {

		Usuarios user = dao.findByNombre(nombre);

		if (user == null)
			return false;

		// Encriptar lo que llega del login
		String passEncriptado = AlgoritmoAES.encrypt(password);
		
		
		/* login -> front
		 *  --> user: Felipe
		 *  --> password 12345
		 *  
		 *  BD 
		 *   --> user: Felipe
		 *  --> password yE8HbL4CR0z3Wai2G/lE/w==
		 *  
		 *  
		 * 
		 * 
		 * 
		 * */

		// Comparar cifrado vs cifrado (NO desencriptar)
		return passEncriptado.equals(user.getUserPassword());
	}

	public boolean login1(String nombre, String password) {
		Usuarios user = dao.findByNombre(nombre);

		if (user == null) {
			System.out.println("Usuario no encontrado en BD");
			return false;
	}
		String passBD = AlgoritmoAES.decrypt(user.getUserPassword().trim());
		
		System.out.println("Pasword " + password);
		System.out.println("Encriptado" + passBD);
		
		return passBD.equals(password);
	}

	@Override
	public List<Usuarios> listar() {
		return dao.findAll();
	}
}
