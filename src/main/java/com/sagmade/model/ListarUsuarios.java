package com.sagmade.model;

public class ListarUsuarios {
	private int idUsuarios;
	private String tipoDocumento;
    private int numeroIdentificacion;
    private String rol;
    private String estadoUsuario;
    
    
	public ListarUsuarios() {
		super();
	}

	public ListarUsuarios(int idUsuarios, String tipoDocumento, int numeroIdentificacion, String rol, String estadoUsuario) {
		super();
		this.idUsuarios = idUsuarios;
		this.tipoDocumento = tipoDocumento;
		this.numeroIdentificacion = numeroIdentificacion;
		this.rol = rol;
		this.estadoUsuario = estadoUsuario;
	}
	
	public int getIdUsuarios() {
		return idUsuarios;
	}
	
	public void setIdUsuarios(int id) {
		this.idUsuarios = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(int numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
    
    
}
