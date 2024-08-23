package Model;

public class T_Modulos_Roles {
	protected int idModulos;
	protected int idRoles;
	
	public T_Modulos_Roles() {
		super();
	}

	public T_Modulos_Roles(int idModulos, int idRoles) {
		super();
		this.idModulos = idModulos;
		this.idRoles = idRoles;
	}

	public int getIdModulos() {
		return idModulos;
	}

	public void setIdModulos(int idModulos) {
		this.idModulos = idModulos;
	}

	public int getIdRoles() {
		return idRoles;
	}

	public void setIdRoles(int idRoles) {
		this.idRoles = idRoles;
	}
}
