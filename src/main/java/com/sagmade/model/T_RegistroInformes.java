package com.sagmade.model;

public class T_RegistroInformes {
	private int idRegistro;
	private int usuario;
	private int areaTrabajo;
	private int actividad;
	private String fechaRegistro;
	private String descripcion;
	private String observacion;
	
	public T_RegistroInformes() {
		super();
	}

	public T_RegistroInformes(int usuario, int areaTrabajo, int actividad, String fechaRegistro, String descripcion,
			String observacion) {
		super();
		this.usuario = usuario;
		this.areaTrabajo = areaTrabajo;
		this.actividad = actividad;
		this.fechaRegistro = fechaRegistro;
		this.descripcion = descripcion;
		this.observacion = observacion;
	}

	public T_RegistroInformes(int idRegistro, int usuario, int areaTrabajo, int actividad, String fechaRegistro,
			String descripcion, String observacion) {
		super();
		this.idRegistro = idRegistro;
		this.usuario = usuario;
		this.areaTrabajo = areaTrabajo;
		this.actividad = actividad;
		this.fechaRegistro = fechaRegistro;
		this.descripcion = descripcion;
		this.observacion = observacion;
	}

	public int getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getAreaTrabajo() {
		return areaTrabajo;
	}

	public void setAreaTrabajo(int areaTrabajo) {
		this.areaTrabajo = areaTrabajo;
	}

	public int getActividad() {
		return actividad;
	}

	public void setActividad(int actividad) {
		this.actividad = actividad;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}	
	
	
}
