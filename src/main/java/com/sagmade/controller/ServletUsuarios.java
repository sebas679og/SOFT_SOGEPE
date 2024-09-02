package com.sagmade.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse.ResponseInfo;
import java.sql.SQLException;
import java.util.List;

import com.sagmade.dao.ModuloUsuarios;
import com.sagmade.model.ListarUsuarios;
import com.sagmade.model.T_Personas;
import com.sagmade.model.T_Usuarios;

@WebServlet(urlPatterns = {"/ServletUsuarios","/nivel-1", "/insertarpu", "/buscarUsuarios", "/actualizarUsuario", "/eliminarUsuario"})
public class ServletUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletUsuarios() {
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
		case "/actualizarUsuario":
			actualizarUsuario(request, response);
			break;
		case "/eliminarUsuario":
			eliminarUsuario(request, response);
		default:
			request.setAttribute("errorMessage", "Error de direccionamiento.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
		}
	}
	
	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int idUsuarios = Integer.parseInt(request.getParameter("idUsuarios"));
        int idPersonas = Integer.parseInt(request.getParameter("idPersonas"));
        ModuloUsuarios moduloUsuarios = new ModuloUsuarios();
        
        try {
			moduloUsuarios.eliminarUsuario(idUsuarios, idPersonas);
			response.sendRedirect("buscarUsuarios");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al Eliminar informacion: " + e.getMessage());
			request.setAttribute("errorMessage", "Error al Eliminar el usuario.");
			request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
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
        T_Personas tpersonas = new T_Personas(numeroIdentificacion, primerNombre, segundoNombre, 
        		primerApellido, segundoApellido, telefono, direccion, genero, tipoDocumento);
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
	
	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        // Recupera los datos enviados por el formulario
	    	String idPersonasStr = request.getParameter("idPersonas");
	        String idUsuariosStr = request.getParameter("idUsuarios");
	        String tipoDocumentoStr = request.getParameter("tipoDocumento");
	        String numeroIdentificacionStr = request.getParameter("numeroIdentificacion");
	        String primerNombre = request.getParameter("primerNombre");
	        String segundoNombre = request.getParameter("segundoNombre");
	        String primerApellido = request.getParameter("primerApellido");
	        String segundoApellido = request.getParameter("segundoApellido");
	        String telefono = request.getParameter("telefono");
	        String direccion = request.getParameter("direccion");
	        String generoStr = request.getParameter("genero");
	        String username = request.getParameter("username");
	        String contraseña = request.getParameter("contraseña");
	        String correo = request.getParameter("correo");
	        String estadoUsuarioStr = request.getParameter("estadoUsuario");
	        String rolStr = request.getParameter("rol");

	        // Convertir parámetros
	        int idPersonas = Integer.parseInt(idPersonasStr != null ? idPersonasStr : "0");
	        int idUsuarios = Integer.parseInt(idUsuariosStr != null ? idUsuariosStr : "0");
	        int tipoDocumento = Integer.parseInt(tipoDocumentoStr != null ? tipoDocumentoStr : "0");
	        int numeroIdentificacion = Integer.parseInt(numeroIdentificacionStr != null ? numeroIdentificacionStr : "0");
	        int genero = Integer.parseInt(generoStr != null ? generoStr : "0");
	        int estadoUsuario = Integer.parseInt(estadoUsuarioStr != null ? estadoUsuarioStr : "0");
	        int rol = Integer.parseInt(rolStr != null ? rolStr : "0");

	        // Crear el objeto de usuario con los nuevos datos
	        T_Personas tpersonas = new T_Personas(idPersonas, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, 
	                segundoApellido, telefono, direccion, genero, tipoDocumento);
	        T_Usuarios tusuarios = new T_Usuarios(idUsuarios, username, contraseña, correo, estadoUsuario, rol);

	        // Mostrar datos para depuración
	        System.out.println("Actualizando usuario con ID: " + idUsuarios);
	        System.out.println("Actualizando persona con ID: " + idPersonas);
	        System.out.println("Datos de persona: " + tpersonas);
	        System.out.println("Datos de usuario: " + tusuarios);
	        System.out.println("Tipo Documento: " + tipoDocumento);
	        System.out.println("Número Identificación: " + numeroIdentificacion);
	        System.out.println("Primer Nombre: " + primerNombre);
	        System.out.println("Segundo Nombre: " + segundoNombre);
	        System.out.println("Primer Apellido: " + primerApellido);
	        System.out.println("Segundo Apellido: " + segundoApellido);
	        System.out.println("Teléfono: " + telefono);
	        System.out.println("Dirección: " + direccion);
	        System.out.println("Género: " + genero);
	        System.out.println("Username: " + username);
	        System.out.println("Contraseña: " + contraseña);
	        System.out.println("Correo: " + correo);
	        System.out.println("Estado Usuario: " + estadoUsuario);
	        System.out.println("Rol: " + rol);


	        // Actualizar el usuario en la base de datos
	        ModuloUsuarios moduloUsuarios = new ModuloUsuarios();
	        moduloUsuarios.actualizarUsuario(tpersonas, tusuarios);

	        response.sendRedirect("buscarUsuarios");  // Redirigir a la lista de usuarios después de actualizar
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Formato de número incorrecto.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Error al actualizar datos.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	    }
	}
}