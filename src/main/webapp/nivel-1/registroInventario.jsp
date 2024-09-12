<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sagmade.model.T_Productos" %>
<%@ page import="com.sagmade.dao.ModuloInventario" %>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Inventario</title>
    <link rel="icon" href="../img/iconoPestaña.jpg" type="image/jpeg">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/Menu.css">
    <link rel="stylesheet" href="../css/formPersona.css">
</head>
<body>
	<%
        // Obtener las categorías desde la base de datos
        ModuloInventario moduloInventario = new ModuloInventario();
        List<T_Productos> listaProductos = moduloInventario.obtenerProductos();
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
        <h1 class="tituloFormUser">Registrar Inventario</h1>
        <div class="cerrar">
            <a href="gestionInventario.jsp">
                <img src="../img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="${pageContext.request.contextPath}/insertarInventario" method="POST">
            <div class="form-grid">
                <div class="frm">
                    <label for="fechaIngreso">Fecha de Ingreso</label>
                    <input type="datetime-local" name="fechaIngreso" id="fechaIngreso" required>
                </div>
                <div class="frm">
                    <label for="producto">Seleccionar Producto</label>
                    <select name="producto" id="producto" required>
                        <option value="" disabled selected>Seleccione Producto</option>
                        <%
                            // Llenar el select con las categorías obtenidas
                            for (T_Productos productos : listaProductos) {
                        %>
                        <option value="<%= productos.getIdProductos() %>"><%= productos.getProducto() %></option>
                        <% 
                            } 
                        %>
                    </select>
                </div>
                <div class="frm">
                    <label for="cantidad">Cantidad</label>
                    <input type="number" name="cantidad" id="cantidad" required>
                </div>
                <div class="btn">
                    <div class="guardar">
                        <button type="submit" id="siguiente">Guardar Registro</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</body>
</html>