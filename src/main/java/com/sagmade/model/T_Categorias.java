package com.sagmade.model;

public class T_Categorias {
	private int idCategoria;
	private String categoria;
	
	//Sobrecarga de Metodos
	public T_Categorias(int idCategoria, String categoria) {
		super();
		this.idCategoria = idCategoria;
		this.categoria = categoria;
	}

	//Getters y Setters
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCtegoria) {
		this.idCategoria = idCtegoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
