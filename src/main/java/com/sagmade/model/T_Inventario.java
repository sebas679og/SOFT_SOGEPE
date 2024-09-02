package com.sagmade.model;

public class T_Inventario {
	private int idInventario;
	private String fechaIngreso;
	private int cantidad;
	private int producto;
	
	public T_Inventario() {
		super();
	}

	public T_Inventario(String fechaIngreso, int cantidad, int producto) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public T_Inventario(int idInventario, String fechaIngreso, int cantidad, int producto) {
		super();
		this.idInventario = idInventario;
		this.fechaIngreso = fechaIngreso;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public int getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}
}
