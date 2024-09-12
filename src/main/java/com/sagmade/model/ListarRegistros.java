package com.sagmade.model;

public class ListarRegistros {
	private int codigo;
	private String username;
	private String fechaRegistro;
	private String area;
	private String actividad;
	
	public ListarRegistros() {
		super();
	}

	public ListarRegistros(int codigo, String username, String fechaRegistro, String area, String actividad) {
		super();
		this.codigo = codigo;
		this.username = username;
		this.fechaRegistro = fechaRegistro;
		this.area = area;
		this.actividad = actividad;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	
	
}
