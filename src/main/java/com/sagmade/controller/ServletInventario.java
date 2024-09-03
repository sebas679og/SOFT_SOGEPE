package com.sagmade.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import com.sagmade.dao.ModuloInventario;
import com.sagmade.dao.ModuloUsuarios;
import com.sagmade.model.ListarInventario;
import com.sagmade.model.ListarProductos;
import com.sagmade.model.ListarUsuarios;
import com.sagmade.model.T_Inventario;
import com.sagmade.model.T_Productos;

@WebServlet(urlPatterns = {"/ServletInventario", "/insertarProducto", "/insertarInventario", "/buscarProducto", "/buscarInventario", "/eliminarProducto", "/eliminarInventario", "/actualizarInventario"})
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
	        case "/buscarProducto":
	            buscarProducto(request, response);
	            break;
	        case "/buscarInventario":
	            buscarInventario(request, response);
	            break;
	        case "/eliminarProducto":
	        	eliminarProducto(request, response);
	        	break;
	        case "/eliminarInventario":
	        	eliminarInventario(request, response);
	        	break;
	        case "/actualizarInventario":
	        	actualizarInventario(request, response);
	        	break;
	        default:
	            request.setAttribute("errorMessage", "Error de direccionamiento");
	            request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	            break;
	    }
	}
	
	private void actualizarInventario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String idInventarioStr = request.getParameter("codigoInventario");
	    String fechaIngreso = request.getParameter("fechaIngreso");
	    String cantidadStr = request.getParameter("cantidad");
	    String productoStr = request.getParameter("producto");

	    int idInventario = 0;
	    int cantidad = 0;
	    int producto = 0;

	    try {
	        if (idInventarioStr != null && !idInventarioStr.trim().isEmpty()) {
	            idInventario = Integer.parseInt(idInventarioStr);
	        } else {
	            throw new IllegalArgumentException("El código de inventario no puede estar vacío.");
	        }

	        if (cantidadStr != null && !cantidadStr.trim().isEmpty()) {
	            cantidad = Integer.parseInt(cantidadStr);
	        } else {
	            throw new IllegalArgumentException("La cantidad no puede estar vacía.");
	        }

	        if (productoStr != null && !productoStr.trim().isEmpty()) {
	            producto = Integer.parseInt(productoStr);
	        } else {
	            throw new IllegalArgumentException("El producto no puede estar vacío.");
	        }

	        T_Inventario tinventario = new T_Inventario(idInventario, fechaIngreso, cantidad, producto);

	        ModuloInventario moduloInventario = new ModuloInventario();
	        moduloInventario.actualizarInventario(tinventario);

	        response.sendRedirect("buscarInventario");
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        System.out.println("Error al convertir número: " + e.getMessage());
	        request.setAttribute("errorMessage", "Error al procesar los datos numéricos.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        System.out.println("Error de argumento: " + e.getMessage());
	        request.setAttribute("errorMessage", e.getMessage());
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error al cargar datos: " + e.getMessage());
	        request.setAttribute("errorMessage", "Error al Registrar nuevos cambios en Inventario.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	    }
	}

	
	private void eliminarInventario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int idInventario = Integer.parseInt(request.getParameter("idInventario"));
		
		ModuloInventario moduloInventario = new ModuloInventario();
		
		try {
	        moduloInventario.eliminarInventario(idInventario);

	        // Cambia a `sendRedirect` para una redirección correcta.
	        response.sendRedirect("buscarInventario");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error al eliminar registro: " + e.getMessage());

	        // Usa `forward` si no se ha comprometido la respuesta y hay un error.
	        if (!response.isCommitted()) {
	            request.setAttribute("errorMessage", "Error al eliminar registro del inventario.");
	            request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	        }
	    }
	}
	
	private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		
		ModuloInventario moduloInventario = new ModuloInventario();
		
		try {
	        moduloInventario.eliminarProducto(codigo);

	        // Cambia a `sendRedirect` para una redirección correcta.
	        response.sendRedirect("buscarProducto");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error al eliminar datos: " + e.getMessage());

	        // Usa `forward` si no se ha comprometido la respuesta y hay un error.
	        if (!response.isCommitted()) {
	            request.setAttribute("errorMessage", "Error al eliminar producto.");
	            request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	        }
	    }
	}
	
	private void buscarInventario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String codigoInventarioStr = request.getParameter("search");
	    int codigoInventario = 0;
	    
	    if (codigoInventarioStr != null && !codigoInventarioStr.trim().isEmpty()) {
	        try {
	        	codigoInventario = Integer.parseInt(codigoInventarioStr);
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "El número de identificación debe ser un número.");
	            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	            return;
	        }
	    }

	    ModuloInventario moduloInventario = new ModuloInventario();
	    try {
	        List<ListarInventario> inventList;
	        
	        // Si 'numeroIdentificacion' es mayor que 0, busca por número de identificación
	        if (codigoInventario > 0) {
	        	inventList = moduloInventario.findInventsByNumeroCodigoI(codigoInventario);
	        } else {
	            // Si 'numeroIdentificacion' no es proporcionado, lista todos los usuarios
	        	inventList = moduloInventario.listAllInventario();
	        }

	        // Establecer la lista de usuarios como atributo en la solicitud
	        request.setAttribute("inventList", inventList);
	        // Reenviar la solicitud a la página de lista de usuarios
	        request.getRequestDispatcher("/nivel-1/consultarInventario.jsp").forward(request, response);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // En caso de error, establece un mensaje de error y reenvía a la página de error
	        request.setAttribute("errorMessage", "Error al recuperar el inventario. Por favor, intente nuevamente.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
	    }
	}
	
	private void buscarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String codigoProductoStr = request.getParameter("search");
	    int codigoProducto = 0;
	    
	    if (codigoProductoStr != null && !codigoProductoStr.trim().isEmpty()) {
	        try {
	        	codigoProducto = Integer.parseInt(codigoProductoStr);
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "El número de identificación debe ser un número.");
	            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	            return;
	        }
	    }

	    ModuloInventario moduloInventario = new ModuloInventario();
	    try {
	        List<ListarProductos> productstList;
	        
	        // Si 'numeroIdentificacion' es mayor que 0, busca por número de identificación
	        if (codigoProducto > 0) {
	        	productstList = moduloInventario.findProductsByNumeroCodigoP(codigoProducto);
	        } else {
	            // Si 'numeroIdentificacion' no es proporcionado, lista todos los usuarios
	        	productstList = moduloInventario.listAllProducts();
	        }

	        // Establecer la lista de usuarios como atributo en la solicitud
	        request.setAttribute("productstList", productstList);
	        // Reenviar la solicitud a la página de lista de usuarios
	        request.getRequestDispatcher("/nivel-1/registroProducto.jsp").forward(request, response);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // En caso de error, establece un mensaje de error y reenvía a la página de error
	        request.setAttribute("errorMessage", "Error al recuperar la lista de Productos. Por favor, intente nuevamente.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
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
	        response.sendRedirect("buscarInventario");
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
			response.sendRedirect("buscarProducto");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al cargar datos" + e.getMessage());
			request.setAttribute("errorMessage", "Error al Registrar Producto.");
	        request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
		}
	}
}