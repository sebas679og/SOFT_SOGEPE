package com.sagmade.model;

public class T_Actividades {
	private int idActividades;
	private String actividad;
	private int area;
	
	public T_Actividades(int idActividades, String actividad) {
		super();
		this.idActividades = idActividades;
		this.actividad = actividad;
	}

	public T_Actividades(int idActividades, String actividad, int area) {
		super();
		this.idActividades = idActividades;
		this.actividad = actividad;
		this.area = area;
	}

	public int getIdActividades() {
		return idActividades;
	}

	public void setIdActividades(int idActividades) {
		this.idActividades = idActividades;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	
	public int getArea() {
		return area;
	}
	
	public void setArea(int area) {
		this.area = area;
	}
}
