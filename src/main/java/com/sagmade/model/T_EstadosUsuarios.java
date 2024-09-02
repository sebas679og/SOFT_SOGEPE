package com.sagmade.model;

public class T_EstadosUsuarios {
	private int idEstado;
	private String estado;
	
	public T_EstadosUsuarios() {
		super();
	}

	public T_EstadosUsuarios(int idEstado, String estado) {
		super();
		this.idEstado = idEstado;
		this.estado = estado;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
