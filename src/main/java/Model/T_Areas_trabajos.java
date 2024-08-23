package Model;

public class T_Areas_trabajos {
	protected int idAreas_trabajos;
	protected String area;
	
	public T_Areas_trabajos() {
		super();
	}

	public T_Areas_trabajos(String area) {
		super();
		this.area = area;
	}

	public T_Areas_trabajos(int idAreas_trabajos, String area) {
		super();
		this.idAreas_trabajos = idAreas_trabajos;
		this.area = area;
	}

	public int getIdAreas_trabajos() {
		return idAreas_trabajos;
	}

	public void setIdAreas_trabajos(int idAreas_trabajos) {
		this.idAreas_trabajos = idAreas_trabajos;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}
