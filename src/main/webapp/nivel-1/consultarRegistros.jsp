<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar Registros</title>
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
        <h1 class="tituloFormUser">Consulta de Registros</h1>
        <div class="cerrar">
            <a href="${pageContext.request.contextPath}/nivel-1/gestionActividades.jsp">
                <img src="${pageContext.request.contextPath}/img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="${pageContext.request.contextPath}/buscarRegistros" method="get" class="buscar">
            <input type="text" id="search" name="search" placeholder="Buscar...">
            <div class="btn-buscar">
                <button type="submit">Buscar</button>
            </div>
        </form>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>Código Registro</th>
                        <th>Usuario</th>
                        <th>Fecha Registro</th>
                        <th>Area de Trabajo</th>
                        <th>Actividad</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                	<c:choose>
                		<c:when test="${not empty registrolist}">
                			<c:forEach var="registro" items="${registrolist}">
                				<tr>
			                        <td>${registro.codigo}</td>
			                        <td>${registro.username}</td>
			                        <td>${registro.fechaRegistro}</td>
			                        <td>${registro.area}</td>
			                        <td>${registro.actividad}</td>
			                        <td>
			                            <button class="action-btn edit-btn">Actualizar</button>
			                            <button class="action-btn delete-btn" onclick="confirmarEliminacion(${registro.codigo})">Eliminar</button>
			                            <button class="action-btn pdf-btn">visualizar Informe</button>
			                        </td>
                    			</tr>
                			</c:forEach>
                		</c:when>
               			<c:otherwise>
               				<tr>
                                <td colspan="6" style="color: red;">No se encontraron registros.</td>
                            </tr>
               			</c:otherwise>
                	</c:choose>
                </tbody>
            </table>
        </div>
    </div>
    
    <script>
	    function confirmarEliminacion(codigo) {
	        if (confirm('¿Estás seguro de que deseas eliminar este reporte?')) {
	            window.location.href = '${pageContext.request.contextPath}/eliminarRegistro?codigo=' + encodeURIComponent(codigo);
	        }
	    }
	</script>
</body>
</html>