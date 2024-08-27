package com.sagmade.model;

public class T_Estado_objetos {
	protected int idEstado_objetos;
	protected String estado;
	
	
	public T_Estado_objetos() {
		super();
	}


	public T_Estado_objetos(String estado) {
		super();
		this.estado = estado;
	}


	public T_Estado_objetos(int idEstado_objetos, String estado) {
		super();
		this.idEstado_objetos = idEstado_objetos;
		this.estado = estado;
	}


	public int getIdEstado_objetos() {
		return idEstado_objetos;
	}


	public void setIdEstado_objetos(int idEstado_objetos) {
		this.idEstado_objetos = idEstado_objetos;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
