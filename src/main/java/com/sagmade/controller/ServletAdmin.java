package com.sagmade.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sagmade.dao.ModuloUsuarios;
import com.sagmade.model.ListarUsuarios;
import com.sagmade.model.T_Personas;
import com.sagmade.model.T_Usuarios;

@WebServlet(urlPatterns = {"/ServletAdmin","/nivel-1", "/insertarpu", "/buscarUsuarios"})
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
		case "/buscarUsuarios":
            buscarUsuarios(request, response);
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
            response.sendRedirect("/SOFT_SOGEPE/buscarUsuarios");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al cargar datos");
            System.out.println(e.getMessage());
        }
    }
	
	private void buscarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String numeroIdentificacionStr = request.getParameter("search");
	    int numeroIdentificacion = 0;
	    
	    if (numeroIdentificacionStr != null && !numeroIdentificacionStr.trim().isEmpty()) {
	        try {
	            numeroIdentificacion = Integer.parseInt(numeroIdentificacionStr);
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "El número de identificación debe ser un número.");
	            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	            return;
	        }
	    }

	    ModuloUsuarios moduloUsuarios = new ModuloUsuarios();
	    try {
	        List<ListarUsuarios> userList;
	        
	        // Si 'numeroIdentificacion' es mayor que 0, busca por número de identificación
	        if (numeroIdentificacion > 0) {
	            userList = moduloUsuarios.findUsersByNumeroIdentificacion(numeroIdentificacion);
	        } else {
	            // Si 'numeroIdentificacion' no es proporcionado, lista todos los usuarios
	            userList = moduloUsuarios.listAllUsers();
	        }

	        // Establecer la lista de usuarios como atributo en la solicitud
	        request.setAttribute("userList", userList);
	        // Reenviar la solicitud a la página de lista de usuarios
	        request.getRequestDispatcher("/nivel-1/listUsers.jsp").forward(request, response);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // En caso de error, establece un mensaje de error y reenvía a la página de error
	        request.setAttribute("errorMessage", "Error al recuperar la lista de usuarios. Por favor, intente nuevamente.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	    }
	}


}


