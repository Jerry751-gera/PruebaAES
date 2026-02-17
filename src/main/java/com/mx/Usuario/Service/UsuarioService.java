package com.mx.Usuario.Service;

import java.util.List;

import com.mx.Usuario.Entity.Usuarios;

// “Esta interfaz puede trabajar con cualquier tipo de objeto”.
public interface UsuarioService<T> {
  
	
    public void registrar(T obj);
    public void editar(T obj);
    public void eliminar(T obj);
    
    
    public List<Usuarios> listar();
    
    public boolean login(String nombre, String password);
}
