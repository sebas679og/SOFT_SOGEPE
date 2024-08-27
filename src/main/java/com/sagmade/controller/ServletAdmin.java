package com.sagmade.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.sagmade.dao.ModuloUsuarios;
import com.sagmade.model.T_Personas;
import com.sagmade.model.T_Usuarios;

@WebServlet(urlPatterns = {"/ServletAdmin","/nivel-1", "/insertarpu"})
public class ServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String actionUsers = request.getServletPath();
		
		switch (actionUsers) {
		case "/insertarpu": 
			insertarUsuario(request, response);
			break;
		
		default:
			System.out.println("No se reconoce la acción enviada por el usuario!!");
		}
	}
	
	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar datos del formulario
        int tipoDocumento = Integer.parseInt(request.getParameter("tipoDocumento"));
        int numeroIdentificacion = Integer.parseInt(request.getParameter("numeroIdentificacion"));
        String primerNombre = request.getParameter("primerNombre");
        String segundoNombre = request.getParameter("segundoNombre");
        String primerApellido = request.getParameter("primerApellido");
        String segundoApellido = request.getParameter("segundoApellido");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        int genero = Integer.parseInt(request.getParameter("genero"));
        
        String username = request.getParameter("username");
        String contraseña = request.getParameter("contraseña");
        String correo = request.getParameter("correo");
        int estadoUsuario = Integer.parseInt(request.getParameter("estadoUsuario"));
        int rol = Integer.parseInt(request.getParameter("rol"));

        // Crear objetos de modelo
        T_Personas tpersonas = new T_Personas(numeroIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono, direccion, genero, tipoDocumento);
        T_Usuarios tusuarios = new T_Usuarios(username, contraseña, correo, estadoUsuario, rol, 0);

        ModuloUsuarios moduloUsuarios = new ModuloUsuarios();

        try {
            moduloUsuarios.insertarUsuario(tpersonas, tusuarios);
            response.sendRedirect("nivel-1/busquedaUsuarios.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al cargar datos");
            System.out.println(e.getMessage());
        }
    }

}
