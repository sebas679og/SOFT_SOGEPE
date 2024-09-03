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

@WebServlet(urlPatterns = {"/ServletUsuarios","/nivel-1", "/insertarpu", "/buscarUsuarios", "/actualizarUsuario", "/eliminarUsuario"})
public class ServletUsuarios extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public ServletUsuarios() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionUsers = request.getServletPath();
        
        try {
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
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/nivel-1/errorPage.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error en la operación: " + e.getMessage());
            request.getRequestDispatcher("/nivel-1/errorPage.jsp").forward(request, response);
        }
    }
    
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idUsuarios = Integer.parseInt(request.getParameter("idUsuarios"));
        int idPersonas = Integer.parseInt(request.getParameter("idPersonas"));
        ModuloUsuarios moduloUsuarios = new ModuloUsuarios();

        moduloUsuarios.eliminarUsuario(idUsuarios, idPersonas);
        response.sendRedirect(request.getContextPath() + "/buscarUsuarios");
    }

    private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
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

        T_Personas tpersonas = new T_Personas(numeroIdentificacion, primerNombre, segundoNombre, 
                primerApellido, segundoApellido, telefono, direccion, genero, tipoDocumento);
        T_Usuarios tusuarios = new T_Usuarios(username, contraseña, correo, estadoUsuario, rol, 0);

        ModuloUsuarios moduloUsuarios = new ModuloUsuarios();
        moduloUsuarios.insertarUsuario(tpersonas, tusuarios);
        response.sendRedirect(request.getContextPath() + "/buscarUsuarios");
    }
    
    private void buscarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String numeroIdentificacionStr = request.getParameter("search");
        int numeroIdentificacion = 0;
        
        if (numeroIdentificacionStr != null && !numeroIdentificacionStr.trim().isEmpty()) {
            try {
                numeroIdentificacion = Integer.parseInt(numeroIdentificacionStr);
            } catch (NumberFormatException e) {
                throw new ServletException("El número de identificación debe ser un número.", e);
            }
        }

        ModuloUsuarios moduloUsuarios = new ModuloUsuarios();
        List<ListarUsuarios> userList;
        
        if (numeroIdentificacion > 0) {
            userList = moduloUsuarios.findUsersByNumeroIdentificacion(numeroIdentificacion);
        } else {
            userList = moduloUsuarios.listAllUsers();
        }

        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/nivel-1/listUsers.jsp").forward(request, response);
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idPersonas = Integer.parseInt(request.getParameter("idPersonas"));
        int idUsuarios = Integer.parseInt(request.getParameter("idUsuarios"));
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

        T_Personas tpersonas = new T_Personas(idPersonas, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, 
                segundoApellido, telefono, direccion, genero, tipoDocumento);
        T_Usuarios tusuarios = new T_Usuarios(idUsuarios, username, contraseña, correo, estadoUsuario, rol);

        ModuloUsuarios moduloUsuarios = new ModuloUsuarios();
        moduloUsuarios.actualizarUsuario(tpersonas, tusuarios);

        response.sendRedirect(request.getContextPath() + "/buscarUsuarios");
    }
}