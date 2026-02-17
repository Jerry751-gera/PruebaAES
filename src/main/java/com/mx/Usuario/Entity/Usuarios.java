package com.mx.Usuario.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USUARIOS_AES")
@NoArgsConstructor
@AllArgsConstructor
@Data


public class Usuarios {

	@Id
	private String id; 
	private String nombre;
	private String app;
	private String taxId;
	private String email;
	private String telefono;
	@Column(name="USER_PASSWORD")
	@com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY)
	private String userPassword;
	
	@Column(name="FECHA_AT")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern="dd-MM-yyyy HH:mm")
	private LocalDateTime fechaAt;
	
	@jakarta.persistence.OneToMany(cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
	@jakarta.persistence.JoinColumn(name="USUARIO_ID")
	private java.util.List<Direcciones> direcciones;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public LocalDateTime getFechaAt() {
		return fechaAt;
	}

	public void setFechaAt(LocalDateTime fechaAt) {
		this.fechaAt = fechaAt;
	}

	public java.util.List<Direcciones> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(java.util.List<Direcciones> direcciones) {
		this.direcciones = direcciones;
	}
	
	}
