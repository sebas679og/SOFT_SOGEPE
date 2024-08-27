package com.sagmade.model;

public class T_Actividades {
	protected int idActividades;
	protected String actividad;
	
	public T_Actividades() {
		super();
	}

	public T_Actividades(String actividad) {
		super();
		this.actividad = actividad;
	}

	public T_Actividades(int idActividades, String actividad) {
		super();
		this.idActividades = idActividades;
		this.actividad = actividad;
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
	
}
