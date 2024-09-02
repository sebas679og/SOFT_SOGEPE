package com.sagmade.model;

public class T_TDocumentos {
	private int idDocumento;
	private String tipoDocumento;
	private String sigla;
	
	public T_TDocumentos() {
		super();
	}

	public T_TDocumentos(int idDocumento, String tipoDocumento) {
		super();
		this.idDocumento = idDocumento;
		this.tipoDocumento = tipoDocumento;
	}

	public T_TDocumentos(String tipoDocumento, String sigla) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.sigla = sigla;
	}

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
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
