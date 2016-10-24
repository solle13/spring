package com.solle13.SistemaWeb.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="sucursales")
public class Sucursales {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int IdPunto;
	
	@NotNull
	@Size(min = 1, max = 80)
	private String NombrePunto;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String Ubicacion;
	
	@NotNull
	private int IdFabrica;
	
	public Sucursales(){}
	
	public Sucursales(int IdPunto){
		this.IdPunto=IdPunto;
	}
	
	public Sucursales(String NombrePunto, String Ubicacion, int IdFabrica){
		this.NombrePunto=NombrePunto;
		this.Ubicacion=Ubicacion;
		this.IdFabrica=IdFabrica;
	}

	public int getIdPunto() {
		return IdPunto;
	}

	public void setIdPunto(int idPunto) {
		IdPunto = idPunto;
	}

	public String getNombrePunto() {
		return NombrePunto;
	}

	public void setNombrePunto(String nombrePunto) {
		NombrePunto = nombrePunto;
	}

	public String getUbicacion() {
		return Ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		Ubicacion = ubicacion;
	}

	public int getIdFabrica() {
		return IdFabrica;
	}

	public void setIdFabrica(int idFabrica) {
		IdFabrica = idFabrica;
	}
	
	
}
