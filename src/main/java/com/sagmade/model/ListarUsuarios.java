package com.sagmade.model;

public class ListarUsuarios {
	private int idUsuarios;
	private String tipoDocumento;
    private int numeroIdentificacion;
    private String rol;
    private String estadoUsuario;
    private int idPersonas;
    private String username;
    
	public ListarUsuarios() {
		super();
	}

	public ListarUsuarios(int idUsuarios, String tipoDocumento, int numeroIdentificacion, String rol,
			String estadoUsuario, int idPersonas, String username) {
		super();
		this.idUsuarios = idUsuarios;
		this.tipoDocumento = tipoDocumento;
		this.numeroIdentificacion = numeroIdentificacion;
		this.rol = rol;
		this.estadoUsuario = estadoUsuario;
		this.idPersonas = idPersonas;
		this.username = username;
	}

	public int getIdUsuarios() {
		return idUsuarios;
	}

	public void setIdUsuarios(int idUsuarios) {
		this.idUsuarios = idUsuarios;
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

	public int getIdPersonas() {
		return idPersonas;
	}

	public void setIdPersonas(int idPersonas) {
		this.idPersonas = idPersonas;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
}