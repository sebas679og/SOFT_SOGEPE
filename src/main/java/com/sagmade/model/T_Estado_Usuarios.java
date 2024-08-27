package com.sagmade.model;

public class T_Estado_Usuarios {
	protected int idEstado_Usuarios;
	protected String estado;
	
	public T_Estado_Usuarios() {
		super();
	}

	public T_Estado_Usuarios(String estado) {
		super();
		this.estado = estado;
	}

	public T_Estado_Usuarios(int idEstado_Usuarios, String estado) {
		super();
		this.idEstado_Usuarios = idEstado_Usuarios;
		this.estado = estado;
	}

	public int getIdEstado_Usuarios() {
		return idEstado_Usuarios;
	}

	public void setIdEstado_Usuarios(int idEstado_Usuarios) {
		this.idEstado_Usuarios = idEstado_Usuarios;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
