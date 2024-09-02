package com.sagmade.model;

public class T_Productos {
	protected int idProductos;
	protected int codigo;
	protected String producto;
	protected int categoria;
	
	//Sobrecarga de Metodos
	public T_Productos() {
		super();
	}
	
	

	public T_Productos(int idProductos, String producto) {
		super();
		this.idProductos = idProductos;
		this.producto = producto;
	}

	public T_Productos(int codigo, String producto, int categoria) {
		super();
		this.codigo = codigo;
		this.producto = producto;
		this.categoria = categoria;
	}

	public T_Productos(int idProductos, int codigo, String producto, int categoria) {
		super();
		this.idProductos = idProductos;
		this.codigo = codigo;
		this.producto = producto;
		this.categoria = categoria;
	}

	//Getters y Setters
	public int getIdProductos() {
		return idProductos;
	}

	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
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

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}	
}
