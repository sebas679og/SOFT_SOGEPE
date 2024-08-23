package Model;

public class T_Generos {
	protected int idGeneros;
	protected String genero;
	
	public T_Generos() {
		super();
	}

	public T_Generos(String genero) {
		super();
		this.genero = genero;
	}

	public T_Generos(int idGeneros, String genero) {
		super();
		this.idGeneros = idGeneros;
		this.genero = genero;
	}

	public int getIdGeneros() {
		return idGeneros;
	}

	public void setIdGeneros(int idGeneros) {
		this.idGeneros = idGeneros;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}
