<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Usuarios</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/iconoPestaña.jpg" type="image/jpeg">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Menu.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/buscarUsers.css">

</head>
<body>
    <header class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="header">
            <a href="${pageContext.request.contextPath}/nivel-1/menu_principalAdmin.jsp">
                <img src="${pageContext.request.contextPath}/img/Letras.png" alt="Platanitos de La Esmeralda" width="200px" class="logo">
            </a>
        </div>
        <div class="icons">
            <img src="${pageContext.request.contextPath}/img/user.png" alt="User" height="30px" class="icon">
            <img src="${pageContext.request.contextPath}/img/menu.png" alt="menu" height="30px" class="icon">
        </div>
    </header>

    <div class="Persona1">
        <h1 class="tituloFormUser">Consulta de Usuarios</h1>
        <div class="cerrar">
            <a href="${pageContext.request.contextPath}/nivel-1/gestionUsuarios.jsp">
                <img src="${pageContext.request.contextPath}/img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form class="buscar">
            <input type="text" id="search" name="search" placeholder="Buscar...">
            <button type="submit">Buscar</button>
        </form>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>Tipo de Documento</th>
                        <th>Número de Identificación</th>
                        <th>Rol</th>
                        <th>Estado del Usuario</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty userList}">
                            <c:forEach var="user" items="${userList}">
                                <tr>
                                    <td>${user.tipoDocumento}</td>
                                    <td>${user.numeroIdentificacion}</td>
                                    <td>${user.rol}</td>
                                    <td>${user.estadoUsuario}</td>
                                    <td>
                                        <button class="action-btn edit-btn">Actualizar</button>
                                        <button class="action-btn delete-btn">Eliminar</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="5" style="color: red;">No se encontraron usuarios.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
