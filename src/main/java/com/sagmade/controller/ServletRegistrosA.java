package com.sagmade.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.sagmade.dao.ModuloRegistros;
import com.sagmade.model.T_Actividades;

@WebServlet(urlPatterns = {"/ServletRegistrosA", "/listarActividades"})
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
			 default:
				 request.setAttribute("errorMessage", "Error de direccionamiento");
		            request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	            break;
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
