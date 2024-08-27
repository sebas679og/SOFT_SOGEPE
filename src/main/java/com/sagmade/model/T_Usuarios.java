package com.sagmade.model;

public class T_Usuarios {
	protected int idUsuarios;
    protected String username;
    protected String contraseña;
    protected String correo;
    protected int estadoUsuario;
    protected int rol;
    protected int persona;

    public T_Usuarios() {
    	super();
    }

    //Constructores con sobrecarga de Metodos
    public T_Usuarios(int idUsuarios, String username, String contraseña, 
            String correo, int estadoUsuario, int rol, int persona) {
        this.idUsuarios = idUsuarios;
        this.username = username;
        this.contraseña = contraseña;
        this.correo = correo;
        this.estadoUsuario = estadoUsuario;
        this.rol = rol;
        this.persona = persona;
    }

    public T_Usuarios(String username, String contraseña, String correo, 
            int estadoUsuario, int rol, int persona) {
        this.username = username;
        this.contraseña = contraseña;
        this.correo = correo;
        this.estadoUsuario = estadoUsuario;
        this.rol = rol;
        this.persona = persona;
    }
    
    //Generacion de Setters y Getters

    public int getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(int idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(int estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getPersona() {
        return persona;
    }

    public void setPersona(int persona) {
        this.persona = persona;
    }

}
