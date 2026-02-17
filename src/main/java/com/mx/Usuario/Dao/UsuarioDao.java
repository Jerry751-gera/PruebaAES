package com.mx.Usuario.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.Usuario.Entity.Usuarios;

// interface de spring data y contiene todos los metodos
@Repository
public interface UsuarioDao extends JpaRepository<Usuarios, String> {

    Usuarios findByNombre(String nombre);
    
    // queryNav
    @Query(nativeQuery = true, value="SELECT * FROM USUARIOS_AES WHERE NOMBRE=:NOMBRE")
    public Usuarios buscarNombre(@Param("NOMBRE")String nombre);
    
}
