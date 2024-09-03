package com.sagmade.model;

public class ListarProductos {
	private int codigo;
	private String producto;
	private String categoria;
	
	public ListarProductos(int codigo, String producto, String categoria) {
		super();
		this.codigo = codigo;
		this.producto = producto;
		this.categoria = categoria;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
