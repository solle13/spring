package com.solle13.SistemaWeb.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="fabrica")
public class Fabricas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int IdFabrica;
	
	@NotNull
	@Size(min = 1, max = 80)
	private String NombreFabrica;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String Ubicacion;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String Lider;
		
	public Fabricas(){}
	
	public Fabricas(int IdFabrica){
		this.IdFabrica=IdFabrica;
	}
	
	public Fabricas(String nombreFabrica, String ubicacion){
		this.NombreFabrica = nombreFabrica;
		this.Ubicacion = ubicacion;
	}
	
	public Fabricas(String nombreFabrica, String ubicacion, String lider) {
		this.NombreFabrica = nombreFabrica;
		this.Ubicacion = ubicacion;
		this.Lider = lider;
	}

	public int getIdFabrica() {
		return IdFabrica;
	}

	public void setIdFabrica(int idFabrica) {
		IdFabrica = idFabrica;
	}

	public String getNombreFabrica() {
		return NombreFabrica;
	}

	public void setNombreFabrica(String nombreFabrica) {
		NombreFabrica = nombreFabrica;
	}

	public String getUbicacion() {
		return Ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		Ubicacion = ubicacion;
	}
	
	public String getLider() {
		return Lider;
	}

	public void setLider(String lider) {
		Lider = lider;
	}
}
