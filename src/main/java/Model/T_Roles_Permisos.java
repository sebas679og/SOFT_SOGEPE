package Model;

public class T_Roles_Permisos {
	protected int idRoles;
	protected int idPermisos;
	
	public T_Roles_Permisos() {
		super();
	}

	public T_Roles_Permisos(int idRoles, int idPermisos) {
		super();
		this.idRoles = idRoles;
		this.idPermisos = idPermisos;
	}

	public int getIdRoles() {
		return idRoles;
	}

	public void setIdRoles(int idRoles) {
		this.idRoles = idRoles;
	}

	public int getIdPermisos() {
		return idPermisos;
	}

	public void setIdPermisos(int idPermisos) {
		this.idPermisos = idPermisos;
	}
}
