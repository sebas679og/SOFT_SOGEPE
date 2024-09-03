<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sagmade.model.T_Categorias" %>
<%@ page import="com.sagmade.dao.ModuloInventario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Producto</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/formPersona.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tablaProd.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/iconoPestaña.jpg" type="image/jpeg">
</head>
<body>
    <%
        // Obtener las categorías desde la base de datos
        ModuloInventario moduloInventario = new ModuloInventario();
        List<T_Categorias> listaCategorias = moduloInventario.obtenerCategorias();
    %>
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
    <div class="Persona">
        <h1 class="tituloFormUser">Registrar Producto</h1>
        <div class="cerrar">
            <a href="${pageContext.request.contextPath}/nivel-1/gestionInventario.jsp">
                <img src="${pageContext.request.contextPath}/img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="${pageContext.request.contextPath}/insertarProducto" method="POST">
            <div class="form-grid">
                <div class="frm">
                    <label for="categoria">Categoria</label>
                    <select name="categoria" id="categoria" required>
                        <option value="" disabled selected>Seleccionar Categoria</option>
                        <%
                            // Llenar el select con las categorías obtenidas
                            for (T_Categorias categoria : listaCategorias) {
                        %>
                        <option value="<%= categoria.getIdCategoria() %>"><%= categoria.getCategoria() %></option>
                        <% 
                            } 
                        %>
                    </select>
                </div>
                <div class="frm">
                    <label for="codigo">Codigo de Identificacion</label>
                    <input type="number" name="codigo" id="codigo" required>
                </div>
                <div class="frm">
                    <label for="producto">Producto</label>
                    <input type="text" name="producto" id="producto" required>
                </div>
                <div class="btn">
                    <div class="guardar">
                        <button type="submit" id="siguiente">Guardar Registro</button>
                    </div>
                </div>
            </div>
        </form>
         <%-- Mostrar mensaje si está presente --%>
        <c:if test="${not empty mensaje}">
            <p class="mensaje">${mensaje}</p>
        </c:if>
         <div class="Producto-list">
            <form action="${pageContext.request.contextPath}/buscarProducto" method="get" class="buscar">
                <input type="text" id="search" name="search" placeholder="Buscar código...">
                <div class="btn-buscar">
                    <button type="submit">Buscar</button>
                </div>
            </form>
            <div class="table-list">
                <table>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Producto</th>
                            <th>Categoria</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                        <c:when test="${not empty productstList}">
                            <c:forEach var="product" items="${productstList}">
                                <tr>
                                    <td>${product.codigo}</td>
                                    <td>${product.producto}</td>
                                    <td>${product.categoria}</td>
                                    <td>
                                        <!-- Botón "Actualizar" con evento onclick para redireccionar
				                        <button class="action-btn edit-btn" 
				                                onclick="window.location.href='${pageContext.request.contextPath}/nivel-1/actualizarUsuario.jsp?id=${user.idUsuarios}'">
				                            Actualizar
				                        </button> -->
                                        <!-- Botón "Eliminar" con evento onclick para confirmar la eliminación -->
    									<button class="action-btn delete-btn" 
    										onclick="confirmarEliminacion(${product.codigo})">Eliminar
    									</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" style="color: red;">No se encontraron usuarios.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script>
	    function confirmarEliminacion(codigo) {
	        if (confirm('¿Estás seguro de que deseas eliminar este producto?')) {
	            window.location.href = '${pageContext.request.contextPath}/eliminarProducto?codigo=' + encodeURIComponent(codigo);
	        }
	    }
	</script>
</body>
</html>
