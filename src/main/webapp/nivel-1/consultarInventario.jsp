<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar Inventario</title>
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
        <h1 class="tituloFormUser">Consulta de Inventario</h1>
        <div class="cerrar">
            <a href="${pageContext.request.contextPath}/nivel-1/gestionInventario.jsp">
                <img src="${pageContext.request.contextPath}/img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="${pageContext.request.contextPath}/buscarInventario" method="get" class="buscar">
            <input type="text" id="search" name="search" placeholder="Buscar...">
            <div class="btn-buscar">
                <button type="submit">Buscar</button>
            </div>
        </form>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>Código Inventario</th>
                        <th>Fecha Ingreso</th>
                        <th>Categoria</th>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty inventList}">
                            <c:forEach var="invent" items="${inventList}">
                                <tr>
                                    <td>${invent.idInventario}</td>
                                    <td>${invent.fechaIngreso}</td>
                                    <td>${invent.categoria}</td>
                                    <td>${invent.producto}</td>
                                    <td>${invent.cantidad}</td>
                                    <td>
                                        <!-- Botón "Actualizar" con evento onclick para redireccionar -->
				                        <button class="action-btn edit-btn" 
				                                onclick="window.location.href='${pageContext.request.contextPath}/nivel-1/actualizarUsuario.jsp?id=${user.idUsuarios}'">
				                            Actualizar
				                        </button>
                                        <!-- Botón "Eliminar" con evento onclick para confirmar la eliminación -->
    									<button class="action-btn delete-btn" 
    										onclick="confirmarEliminacion(${invent.idInventario})">Eliminar
    									</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" style="color: red;">No se encontro el inventario.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </div>
    <script>
	    function confirmarEliminacion(idInventario) {
	        if (confirm('¿Estás seguro de que deseas eliminar este registro?')) {
	            window.location.href = '${pageContext.request.contextPath}/eliminarInventario?idInventario=' + encodeURIComponent(idInventario);
	        }
	    }
	</script>
</body>
</html>