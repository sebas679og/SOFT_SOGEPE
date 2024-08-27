package com.sagmade.model;


public class T_Personas {
	protected int idPersonas;
    protected int tipoDocumento;
    protected int numeroIdentificacion;
    protected String primerNombre;
    protected String segundoNombre;
    protected String primerApellido;
    protected String segundoApellido;
    protected String telefono;
    protected String direccion;
    protected int genero;
    
    //Sobrecarga de metodos
	public T_Personas(int tipoDocumento2, String numeroIdentificacion2, String primerNombre2, String segundoNombre2, String primerApellido2, String segundoApellido2, String telefono2, String direccion2, int genero2) {
		super();
	}

	public T_Personas(int numeroIdentificacion, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String telefono, String direccion, int genero, int tipoDocumento) {
		super();
		this.numeroIdentificacion = numeroIdentificacion;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.genero = genero;
		this.tipoDocumento = tipoDocumento;
	}

	public T_Personas(int idPersonas, int numeroIdentificacion, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, String telefono, String direccion, int genero,
			int tipoDocumento) {
		super();
		this.idPersonas = idPersonas;
		this.numeroIdentificacion = numeroIdentificacion;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.genero = genero;
		this.tipoDocumento = tipoDocumento;
	}

	//getters y setters
	public int getIdPersonas() {
		return idPersonas;
	}

	public void setIdPersonas(int idPersonas) {
		this.idPersonas = idPersonas;
	}

	public int getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(int numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public int getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	 
}
