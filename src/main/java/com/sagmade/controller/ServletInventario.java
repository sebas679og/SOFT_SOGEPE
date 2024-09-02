package com.sagmade.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import com.sagmade.dao.ModuloInventario;
import com.sagmade.model.T_Inventario;
import com.sagmade.model.T_Productos;

@WebServlet(urlPatterns = {"/ServletInventario", "/insertarProducto", "/insertarInventario"})
public class ServletInventario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletInventario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String actionInventario = request.getServletPath();
		
		 switch (actionInventario) {
	        case "/insertarProducto":
	            insertarProducto(request, response);
	            break;
	        case "/insertarInventario":
	            insertarInventario(request, response);
	            break; 
	        default:
	            request.setAttribute("errorMessage", "Error de direccionamiento");
	            request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	            break;
	    }
	}

	private void insertarInventario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String fechaIngreso = request.getParameter("fechaIngreso");
	    int cantidad = Integer.parseInt(request.getParameter("cantidad"));
	    int producto = Integer.parseInt(request.getParameter("producto"));

	    T_Inventario tinventario = new T_Inventario(fechaIngreso, cantidad, producto);

	    ModuloInventario moduloInventario = new ModuloInventario();

	    try {
	        moduloInventario.insertarInventario(tinventario);

	        // Cambia a `sendRedirect` para una redirección correcta.
	        response.sendRedirect(request.getContextPath() + "/nivel-1/consultarInventario.jsp");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error al cargar datos: " + e.getMessage());

	        // Usa `forward` si no se ha comprometido la respuesta y hay un error.
	        if (!response.isCommitted()) {
	            request.setAttribute("errorMessage", "Error al Registrar Inventario.");
	            request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	        }
	    }
	}



	private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recuperar Datos del Formulario
		int categoria = Integer.parseInt(request.getParameter("categoria"));
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		String producto = request.getParameter("producto");
		
		//Creacion objetos de modelo
		T_Productos tproductos = new T_Productos(codigo, producto, categoria);
		
		ModuloInventario moduloInventario = new ModuloInventario();
		
		try {
			moduloInventario.insertarProducto(tproductos);
			response.sendRedirect(request.getContextPath() + "/nivel-1/registroInventario.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al cargar datos" + e.getMessage());
			request.setAttribute("errorMessage", "Error al Registrar Producto.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
		}
	}
}