package com.solle13.SistemaWeb.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Ventas")
public class Ventas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int IdVenta;
	
	@NotNull
	private int Cantidad;
	
	@NotNull
	@Size(min = 1, max = 20)
	private String Fecha;
	
	@NotNull
	private int IdPunto;
	
	@NotNull
	private int IdFabrica;
	
	public Ventas(){}
	
	public Ventas(int IdVenta){
		this.IdVenta=IdVenta;
	}
	
	public Ventas(int Cantidad, String Fecha, int IdPunto, int IdFabrica){
		this.Cantidad=Cantidad;
		this.Fecha=Fecha;
		this.IdPunto=IdPunto;
		this.IdFabrica=IdFabrica;
	}

	public int getIdVenta() {
		return IdVenta;
	}

	public void setIdVenta(int idVenta) {
		IdVenta = idVenta;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public int getIdPunto() {
		return IdPunto;
	}

	public void setIdPunto(int idPunto) {
		IdPunto = idPunto;
	}

	public int getIdFabrica() {
		return IdFabrica;
	}

	public void setIdFabrica(int idFabrica) {
		IdFabrica = idFabrica;
	}
	
	
}
