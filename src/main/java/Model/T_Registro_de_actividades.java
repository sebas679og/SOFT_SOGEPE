package Model;

public class T_Registro_de_actividades {
	protected int idRegistro_de_actividades;
	protected String descripcion;
	protected String observacion;
	protected int areaTrabajo;
	protected int actividad;
	protected String fechaRegistro;
	protected int usuario;
	
	public T_Registro_de_actividades() {
		super();
	}

	public T_Registro_de_actividades(String descripcion, String observacion, int areaTrabajo, int actividad,
			String fechaRegistro, int usuario) {
		super();
		this.descripcion = descripcion;
		this.observacion = observacion;
		this.areaTrabajo = areaTrabajo;
		this.actividad = actividad;
		this.fechaRegistro = fechaRegistro;
		this.usuario = usuario;
	}

	public T_Registro_de_actividades(int idRegistro_de_actividades, String descripcion, String observacion,
			int areaTrabajo, int actividad, String fechaRegistro, int usuario) {
		super();
		this.idRegistro_de_actividades = idRegistro_de_actividades;
		this.descripcion = descripcion;
		this.observacion = observacion;
		this.areaTrabajo = areaTrabajo;
		this.actividad = actividad;
		this.fechaRegistro = fechaRegistro;
		this.usuario = usuario;
	}

	public int getIdRegistro_de_actividades() {
		return idRegistro_de_actividades;
	}

	public void setIdRegistro_de_actividades(int idRegistro_de_actividades) {
		this.idRegistro_de_actividades = idRegistro_de_actividades;
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

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
}
