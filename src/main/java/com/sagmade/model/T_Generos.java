package com.sagmade.model;

public class T_Generos {
	private int idGeneros;
	private String genero;
	
	public T_Generos() {
		super();
	}

	public T_Generos(int idGeneros, String genero) {
		super();
		this.idGeneros = idGeneros;
		this.genero = genero;
	}

	public int getIdGeneros() {
		return idGeneros;
	}

	public void setIdGeneros(int idGeneros) {
		this.idGeneros = idGeneros;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
}
