package com.sagmade.model;

public class T_Tipo_Documentos {
	protected int idTipo_Documentos;
	protected String tipoDocumento;
	protected String sigla;
	
	public T_Tipo_Documentos() {
		super();
	}

	public T_Tipo_Documentos(String tipoDocumento, String sigla) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.sigla = sigla;
	}

	public T_Tipo_Documentos(int idTipo_Documentos, String tipoDocumento, String sigla) {
		super();
		this.idTipo_Documentos = idTipo_Documentos;
		this.tipoDocumento = tipoDocumento;
		this.sigla = sigla;
	}

	public int getIdTipo_Documentos() {
		return idTipo_Documentos;
	}

	public void setIdTipo_Documentos(int idTipo_Documentos) {
		this.idTipo_Documentos = idTipo_Documentos;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
}
