package Model;

public class T_Permisos {
	protected int idPermisos;
	protected String permiso;
	protected String descripcionPermiso;
	
	public T_Permisos() {
		super();
	}

	public T_Permisos(String permiso, String descripcionPermiso) {
		super();
		this.permiso = permiso;
		this.descripcionPermiso = descripcionPermiso;
	}

	public T_Permisos(int idPermisos, String permiso, String descripcionPermiso) {
		super();
		this.idPermisos = idPermisos;
		this.permiso = permiso;
		this.descripcionPermiso = descripcionPermiso;
	}

	public int getIdPermisos() {
		return idPermisos;
	}

	public void setIdPermisos(int idPermisos) {
		this.idPermisos = idPermisos;
	}

	public String getPermiso() {
		return permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}

	public String getDescripcionPermiso() {
		return descripcionPermiso;
	}

	public void setDescripcionPermiso(String descripcionPermiso) {
		this.descripcionPermiso = descripcionPermiso;
	}
}
