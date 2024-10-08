<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sagmade.model.T_TDocumentos" %>
<%@ page import="com.sagmade.model.T_Roles" %>
<%@ page import="com.sagmade.model.T_Generos" %>
<%@ page import="com.sagmade.model.T_EstadosUsuarios" %>
<%@ page import="com.sagmade.dao.ModuloUsuarios" %>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro Usuario</title>
    <link rel="icon" href="../img/iconoPestaña.jpg" type="image/jpeg">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/Menu.css">
    <link rel="stylesheet" href="../css/formPersona.css">
</head>
<body>
	<%
        // Obtener las categorías desde la base de datos
        ModuloUsuarios moduloUsuarios = new ModuloUsuarios();
        List<T_TDocumentos> listaDocumentos = moduloUsuarios.obtenerDocumentos();
        List<T_Roles> listaRoles = moduloUsuarios.obtenerRoles();
        List<T_Generos> listaGeneros = moduloUsuarios.obtenerGeneros();
        List<T_EstadosUsuarios> listaEstados = moduloUsuarios.obtenerEstados();
    %>
    <header class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="header">
            <a href="menu_principalAdmin.jsp">
                <img src="../img/Letras.png" alt="Platanitos de La Esmeralda" width="200px" class="logo">
            </a>
        </div>
        <div class="icons">
            <img src="../img/user.png" alt="User" height="30px" class="icon">
            <img src="../img/menu.png" alt="menu" height="30px" class="icon">
        </div>
    </header>

    <div class="Persona">
        <h1 class="tituloFormUser">Formulario de Registro Usuario</h1>
        <div class="cerrar">
            <a href="gestionUsuarios.jsp">
                <img src="../img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="/SOFT_SOGEPE/insertarpu" method="POST">
            <div class="form-grid">
                <div class="frm">
                    <label for="tipoDocumento">Tipo de Documento</label>
                    <select class="select" name="tipoDocumento" id="tipoDocumento" required>
                        <option value="" disabled selected>Seleccione Tipo de Identificacion</option>
						<%
                            // Llenar el select con las categorías obtenidas
                            for (T_TDocumentos documentos : listaDocumentos) {
                        %>
                        <option value="<%= documentos.getIdDocumento() %>"><%= documentos.getTipoDocumento() %></option>
                        <% 
                            } 
                        %>
                    </select>
                </div>
                <div class="frm">
                    <label for="numeroIdentificacion">Número de Identificacion</label>
                    <input type="number" name="numeroIdentificacion" id="numeroIdentificacion" required>
                </div>
                <div class="frm">
                    <label for="primerNombre">Primer Nombre</label>
                    <input type="text" name="primerNombre" id="primerNombre" required>
                </div>
                <div class="frm">
                    <label for="segundoNombre">Segundo Nombre</label>
                    <input type="text" name="segundoNombre" id="segundoNombre">
                </div>
                <div class="frm">
                    <label for="primerApellido">Primer Apellido</label>
                    <input type="text" name="primerApellido" id="primerApellido" required>
                </div>
                <div class="frm">
                    <label for="segundoApellido">Segundo Apellido</label>
                    <input type="text" name="segundoApellido" id="segundoApellido" required>
                </div>
                <div class="frm">
                    <label for="telefono">Telefono</label>
                    <input type="tel" name="telefono" id="telefono" required>
                </div>
                <div class="frm">
                    <label for="direccion">Dirección</label>
                    <input type="text" name="direccion" id="direccion" required>
                </div>
                <div class="frm">
                    <label for="genero">Genero</label>
                    <select class="select" name="genero" id="genero" required>
                        <option value="" disabled selected>Seleccione Genero</option>
                        <%
                            // Llenar el select con las categorías obtenidas
                            for (T_Generos generos : listaGeneros) {
                        %>
                        <option value="<%= generos.getIdGeneros() %>"><%= generos.getGenero() %></option>
                        <% 
                            } 
                        %>
                    </select>
                </div>
                <div class="frm">
                    <label for="rol">Rol de Usuario</label>
                    <select class="select" name="rol" id="rol" required>
                        <option value="" disabled selected>Seleccione Rol</option>
                        <%
                            // Llenar el select con las categorías obtenidas
                            for (T_Roles roles : listaRoles) {
                        %>
                        <option value="<%= roles.getIdRoles() %>"><%= roles.getRol() %></option>
                        <% 
                            } 
                        %>
                        
                    </select>
                </div>
                <div class="frm">
                    <label for="username">Usuario</label>
                    <input type="text" name="username" id="username" required>
                </div>
                <div class="frm">
                    <label for="contraseña">Contraseña</label>
                    <input type="password" name="contraseña" id="contraseña" required>
                </div>
                <div class="frm">
                    <label for="correo">Correo</label>
                    <input type="email" name="correo" id="correo" required>
                </div>
                <div class="frm">
                    <label for="estadoUsuario">Estado del Usuario</label>
                    <select class="select" name="estadoUsuario" id="estadoUsuario" required>
                        <option value="" disabled selected>Seleccione Estado</option>
                        <%
                            // Llenar el select con las categorías obtenidas
                            for (T_EstadosUsuarios estados : listaEstados) {
                        %>
                        <option value="<%= estados.getIdEstado() %>"><%= estados.getEstado() %></option>
                        <% 
                            } 
                        %>
                    </select>
                </div>
                <div class="btn">
                    <div class="guardar">
                        <button type="submit" id="siguiente">Guardar Registro</button>
                    </div>
                    <div class="limpiar">
                        <button type="reset">Limpiar</button>
                    </div>
                </div>
            </div>
        </form>
    </div>    
</body>
</html>