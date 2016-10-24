package com.solle13.SistemaWeb.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Usuarios")
public class Usuarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int IdUsuario;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String Usuario;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String Pass;
	
	public Usuarios(){}
	
	public Usuarios(int idUsuario) {
		this.IdUsuario = idUsuario;
	}
	
	public Usuarios(String Usuario, String pass) {
		this.Usuario = Usuario;
		this.Pass = pass;
	}

	public int getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	
}
