package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.ValidarIngreso;

public class ValidarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ValidarServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String contraseña = request.getParameter("contraseña");
		String tipoUsuario = request.getParameter("tipoUsuario");

		ValidarIngreso validarIngreso = new ValidarIngreso();

		// Valida si el usuario es válido
		boolean esValido = validarIngreso.validarUsuario(username, contraseña, tipoUsuario);

		if (esValido) {
		    // Redirige según el tipo de usuario
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
		        default:
		            // Si el tipo de usuario no es válido, redirige a la página de inicio de sesión con un mensaje de error
		            request.setAttribute("errorMessage", "Tipo de usuario no válido");
		            request.getRequestDispatcher("index.jsp").forward(request, response);
		            break;
		    }
		} else {
		    // Si las credenciales son incorrectas, redirige a la página de inicio de sesión con un mensaje de error
		    request.setAttribute("errorMessage", "Credenciales incorrectas");
		    request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

}
