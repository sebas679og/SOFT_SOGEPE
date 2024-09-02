package com.sagmade.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


import com.sagmade.dao.ValidarIngreso;

@WebServlet({"/ServletController", "/Validar"})
public class ServletValidar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ServletValidar() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
String actionUsers = request.getServletPath();
		
		switch (actionUsers) {
		case "/Validar": {
			ValidarUsuario(request, response);
			break;
		}
		default:
			System.out.println("No se reconoce la acción enviada por el usuario!!");
		}
	}

	private void ValidarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String username = request.getParameter("username");
	        String contraseña = request.getParameter("contraseña");
	        String tipoUsuario = request.getParameter("tipoUsuario");

	        ValidarIngreso validarIngreso = new ValidarIngreso();
	        
	        // Validar el usuario. Asegúrate de que `validarUsuario` maneje SQLException internamente
	        boolean esValido = validarIngreso.validarUsuario(username, contraseña, tipoUsuario);

	        if (esValido) {
	            switch (tipoUsuario) {
	                case "Administrador":
	                    response.sendRedirect("nivel-1/menu_principalAdmin.jsp");
	                    break;
	                case "Gerente":
	                case "Funcionario de Bodega":
	                    response.sendRedirect("nivel-2/menu_principal.jsp");
	                    break;
	                case "Funcionario de Producción":
	                case "Funcionario de Entrega":
	                    response.sendRedirect("nivel-3/menu_principal.jsp");
	                    break;
	                default:
	                    request.setAttribute("errorMessage", "Tipo de usuario no válido");
	                    request.getRequestDispatcher("index.jsp").forward(request, response);
	                    break;
	            }
	        } else {
	            request.setAttribute("errorMessage", "Credenciales incorrectas");
	            request.getRequestDispatcher("index.jsp").forward(request, response);
	        }
	    } catch (IOException e) {
	        e.printStackTrace(); // Agrega un mensaje específico o maneja la excepción adecuadamente
	        request.setAttribute("errorMessage", "Error de entrada/salida.");
	        request.getRequestDispatcher("index.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace(); // Agrega un mensaje específico o maneja la excepción adecuadamente
	        request.setAttribute("errorMessage", "Ocurrió un error inesperado.");
	        request.getRequestDispatcher("index.jsp").forward(request, response);
	    }
	}

}