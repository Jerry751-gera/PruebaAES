package com.mx.Usuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mx.Usuario.Entity.Usuarios;
import com.mx.Usuario.Service.UsuarioService;
import com.mx.Usuario.Service.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl service;

	// http://localhost:9009/usuarios/lista
	@GetMapping(value = "lista")
	public List<Usuarios> lista() {
		return service.listar();
	}

	// http://localhost:9009/usuarios/registrar
	@PostMapping(value = "/registrar")
	public String registrar(@RequestBody Usuarios usuario) {
		service.registrar(usuario);
		return "Usuario registrado con EXITO!";
	}

	// http://localhost:9009/usuarios/editar
	@PutMapping(value = "/editar")
	public String editar(@RequestBody Usuarios usuario) {
		service.editar(usuario);
		return "Usuario Actualizado con EXITO!";
	}

	// http://localhost:9009/usuarios/eliminar
	@DeleteMapping(value = "/eliminar")
	public String eliminar(@RequestBody Usuarios usuario) {
		service.eliminar(usuario);
		return "Usuario Eliminado con EXITO!";
	}

	// http://localhost:9009/usuarios/login1
	@PostMapping(value = "/login1")
	public String login1(@RequestBody Usuarios user) {

		boolean acceso = service.login1(user.getNombre(), user.getUserPassword());
		if(acceso) {
			return "Login correcto";
		}else {
			return "Credenciales incorrecto";
		}
	}
}
