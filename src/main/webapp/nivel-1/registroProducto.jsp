<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sagmade.model.T_Categorias" %>
<%@ page import="com.sagmade.dao.ModuloInventario" %>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Producto</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/Menu.css">
    <link rel="stylesheet" href="../css/formPersona.css">
    <link rel="icon" href="../img/iconoPestaña.jpg" type="image/jpeg">
</head>
<body>
    <%
        // Obtener las categorías desde la base de datos
        ModuloInventario moduloInventario = new ModuloInventario();
        List<T_Categorias> listaCategorias = moduloInventario.obtenerCategorias();
    %>
    <header class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="header">
            <a href="${pageContext.request.contextPath}/menu_principalAdmin.jsp">
                <img src="../img/Letras.png" alt="Platanitos de La Esmeralda" width="200px" class="logo">
            </a>
        </div>
        <div class="icons">
            <img src="../img/user.png" alt="User" height="30px" class="icon">
            <img src="../img/menu.png" alt="menu" height="30px" class="icon">
        </div>
    </header>
    <div class="Persona">
        <h1 class="tituloFormUser">Registrar Producto</h1>
        <div class="cerrar">
            <a href="${pageContext.request.contextPath}/nivel-1/gestionInventario.jsp">
                <img src="../img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="${pageContext.request.contextPath}/insertarProducto" method="POST">
            <div class="form-grid">
                <div class="frm">
                    <label for="categoria">Categoria</label>
                    <select name="categoria" id="categoria">
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
                    <input type="number" name="codigo" id="codigo">
                </div>
                <div class="frm">
                    <label for="producto">Producto</label>
                    <input type="text" name="producto" id="producto">
                </div>
                <div class="btn">
                    <div class="guardar">
                        <button type="submit" id="siguiente">Guardar Registro</button>
                    </div>
                    <div class="limpiar">
                        <button type="button" onclick="">Eliminar Producto</button>
                    </div>
                </div>
            </div>
        </form>
         <%-- Mostrar mensaje si está presente --%>
        <c:if test="${not empty mensaje}">
            <p class="mensaje">${mensaje}</p>
        </c:if>
    </div>
</body>
</html>
