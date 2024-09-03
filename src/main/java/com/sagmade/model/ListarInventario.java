package com.sagmade.model;

public class ListarInventario {
	private int idInventario;
	private String fechaIngreso;
	private String categoria;
	private String producto;
	private int cantidad;
	
	public ListarInventario(int idInventario, String fechaIngreso, String categoria, String producto, int cantidad) {
		super();
		this.idInventario = idInventario;
		this.fechaIngreso = fechaIngreso;
		this.categoria = categoria;
		this.producto = producto;
		this.cantidad = cantidad;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
