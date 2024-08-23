package Model;

public class T_Roles {
	protected int idRoles;
    protected String Rol;

    //Constructores con sobrecarga de Metodos
    public T_Roles() {
    	super();
    }

    public T_Roles(String Rol) {
        this.Rol = Rol;
    }

    public T_Roles(int idRoles, String Rol) {
        this.idRoles = idRoles;
        this.Rol = Rol;
    }
    
    //Generacion de Setters y Getters

    public int getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(int idRoles) {
        this.idRoles = idRoles;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

}
