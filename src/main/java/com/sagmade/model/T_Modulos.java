package com.sagmade.model;

public class T_Modulos {
	protected int idModulos;
	protected String modulo;
	
	public T_Modulos() {
		super();
	}

	public T_Modulos(String modulo) {
		super();
		this.modulo = modulo;
	}

	public T_Modulos(int idModulos, String modulo) {
		super();
		this.idModulos = idModulos;
		this.modulo = modulo;
	}

	public int getIdModulos() {
		return idModulos;
	}

	public void setIdModulos(int idModulos) {
		this.idModulos = idModulos;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
}