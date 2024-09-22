package com.sagmade.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.sagmade.dao.ModuloRegistros;
import com.sagmade.model.ListarRegistros;
import com.sagmade.model.T_Actividades;
import com.sagmade.model.T_RegistroInformes;

@WebServlet(urlPatterns = {"/ServletRegistrosA", "/listarActividades", "/insertarRegistro", "/buscarRegistros", "/eliminarRegistro", "/actualizarReporte"})
@MultipartConfig
public class ServletRegistrosA extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletRegistrosA() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
	}
	
	protected void procesRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionRegistro = request.getServletPath();
		
		 switch (actionRegistro) {
			 case "/listarActividades":
				 listaractividades(request, response);
				 break;
			 case "/insertarRegistro" :
				 insertarRegistro(request, response);
				 break;
			 case "/buscarRegistros":
				 buscarRegistros(request, response);
				 break;
			 case "/eliminarRegistro":
				 eliminarRegistro(request, response);
			 break;
			 case "/actualizarReporte":
				 actualizarRegistro(request, response);
				 break;
			 default:
				 request.setAttribute("errorMessage", "Error de direccionamiento");
		            request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	            break;
		 }
	}
	
	private void actualizarRegistro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    int idRegistro = Integer.parseInt(request.getParameter("codigoRegistro"));
	    int usuario = Integer.parseInt(request.getParameter("username"));
	    int areaTrabajo = Integer.parseInt(request.getParameter("area"));
	    int actividad = Integer.parseInt(request.getParameter("actividad"));
	    String fechaRegistro = request.getParameter("fechaRegistro");
	    String descripcion = request.getParameter("descripcion");
	    String observacion = request.getParameter("observacion");

	    T_RegistroInformes tRegistros = new T_RegistroInformes(idRegistro, usuario, areaTrabajo, actividad, fechaRegistro, descripcion, observacion);
	    
	    ModuloRegistros moduloRegistros = new ModuloRegistros();
	    
	    try {
	        moduloRegistros.actualizarRegistro(tRegistros);
	        // Redirigir solo si la respuesta no ha sido comprometida
	        if (!response.isCommitted()) {
	            response.sendRedirect("buscarRegistros");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error al cargar datos: " + e.getMessage());

	        if (!response.isCommitted()) {
	            request.setAttribute("errorMessage", "Error al actualizar Reporte.");
	            request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	        }
	    }
	}

	
	private void eliminarRegistro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    int codigo = Integer.parseInt(request.getParameter("codigo"));

	    ModuloRegistros moduloRegistro = new ModuloRegistros();
	    
	    try {
	        moduloRegistro.eliminarReporte(codigo);
	        if (!response.isCommitted()) {
	            response.sendRedirect("buscarRegistros");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error al eliminar reporte: " + e.getMessage());

	        if (!response.isCommitted()) {
	            request.setAttribute("errorMessage", "Error al eliminar Reporte.");
	            request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	        }
	    }
	}

	
	private void buscarRegistros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String codigoRegistroStr = request.getParameter("search");
	    int codigoRegistro = 0;

	    if (codigoRegistroStr != null && !codigoRegistroStr.trim().isEmpty()) {
	        try {
	            codigoRegistro = Integer.parseInt(codigoRegistroStr);
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "El número de identificación debe ser un número.");
	            if (!response.isCommitted()) {
	                request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	            }
	            return;
	        }
	    }

	    ModuloRegistros moduloRegistro = new ModuloRegistros();
	    try {
	        List<ListarRegistros> registrolist;
	        if (codigoRegistro > 0) {
	            registrolist = moduloRegistro.listarRegistrosPorCodigo(codigoRegistro);
	        } else {
	            registrolist = moduloRegistro.listAllRegistros();
	        }

	        request.setAttribute("registrolist", registrolist);
	        if (!response.isCommitted()) {
	            request.getRequestDispatcher("/nivel-1/consultarRegistros.jsp").forward(request, response);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Error al recuperar Registros. Por favor, intente nuevamente.");
	        if (!response.isCommitted()) {
	            request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	        }
	    }
	}

	
	private void insertarRegistro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Recuperar Datos del Formulario
		int usuario = Integer.parseInt(request.getParameter("username"));
		int areaTrabajo = Integer.parseInt(request.getParameter("area"));
		int actividad = Integer.parseInt(request.getParameter("actividad"));
		String fechaRegistro = request.getParameter("fechaRegistro");
		String descripcion = request.getParameter("descripcion");
		String observacion = request.getParameter("descripcion");
		
		//Creacion objetos de modelo
		T_RegistroInformes tregistros = new T_RegistroInformes(usuario, areaTrabajo, actividad, fechaRegistro, descripcion, observacion);
		
		ModuloRegistros moduloRegistros = new ModuloRegistros();
		
		try {
			moduloRegistros.insertarRegistro(tregistros);
			response.sendRedirect("buscarRegistros");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al cargar datos" + e.getMessage());
			request.setAttribute("errorMessage", "Error al Registrar Actividad.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
		}
		
	}

	private void listaractividades(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idArea = Integer.parseInt(request.getParameter("idArea"));
	        ModuloRegistros moduloRegistros = new ModuloRegistros();
	        List<T_Actividades> listarActividades = moduloRegistros.obtenerActividadesPorArea(idArea);
	
	        // Convertir la lista de subcategorías a JSON
	        Gson gson = new Gson();
	        String jsonResponse = gson.toJson(listarActividades);
	
	        // Establecer la respuesta como JSON
	        response.setContentType("application/json");
	        response.getWriter().write(jsonResponse);
		} catch (NumberFormatException e) {
			e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID de área proporcionado no es válido.");
		} catch (Exception e) {
			e.printStackTrace();
	        // En caso de error, establece un mensaje de error y reenvía a la página de error
	        request.setAttribute("errorMessage", "Error al recuperar el inventario. Por favor, intente nuevamente.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
		}
	}
}
