package Model;

public class T_Composicion {
	protected int idComposicion;
	protected String composicion;
	
	public T_Composicion() {
		super();
	}

	public T_Composicion(String composicion) {
		super();
		this.composicion = composicion;
	}

	public T_Composicion(int idComposicion, String composicion) {
		super();
		this.idComposicion = idComposicion;
		this.composicion = composicion;
	}

	public int getIdComposicion() {
		return idComposicion;
	}

	public void setIdComposicion(int idComposicion) {
		this.idComposicion = idComposicion;
	}

	public String getComposicion() {
		return composicion;
	}

	public void setComposicion(String composicion) {
		this.composicion = composicion;
	}
}
