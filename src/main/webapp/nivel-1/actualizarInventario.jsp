<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sagmade.model.T_Productos" %>
<%@ page import="com.sagmade.dao.ModuloInventario" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actualizar Inventario</title>
    <link rel="icon" href="../img/iconoPestaña.jpg" type="image/jpeg">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/Menu.css">
    <link rel="stylesheet" href="../css/formPersona.css">
</head>
<body>
    <%
        // Obtener las categorías y productos desde la base de datos
        ModuloInventario moduloInventario = new ModuloInventario();
        List<T_Productos> listaProductos = moduloInventario.obtenerProductos();
        
        // Obtener el ID del inventario desde la solicitud
        String idInventario = request.getParameter("id"); // Suponiendo que pasas el ID del inventario como parámetro
        
        // Variables para almacenar los datos del inventario
        String fechaIngreso = "";
        String productoSeleccionado = "";
        int cantidad = 0;
        
        if (idInventario != null) {
            // Consultar los datos del inventario
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            try {
                String url = "jdbc:mysql://localhost:3306/sogepe?useSSL=false"; 
                String usernameDB = "root"; 
                String passwordDB = "sebas1234"; 
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, usernameDB, passwordDB);
                
                String sql = "SELECT i.idInventario AS id_inventario, i.fechaIngreso AS fecha_ingreso, p.producto AS producto, i.cantidad AS cantidad " +
                             "FROM inventario i " +
                             "JOIN productos p ON i.producto = p.idProductos " +
                             "WHERE i.idInventario = ?";
                
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, idInventario);
                rs = stmt.executeQuery();
                
                if (rs.next()) {
                    fechaIngreso = rs.getString("fecha_ingreso");
                    productoSeleccionado = rs.getString("producto");
                    cantidad = rs.getInt("cantidad");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
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
        <h1 class="tituloFormUser">Actualizar Inventario</h1>
        <div class="cerrar">
            <a href="${pageContext.request.contextPath}/buscarInventario">
                <img src="${pageContext.request.contextPath}/img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="${pageContext.request.contextPath}/actualizarInventario" method="POST">
            <div class="form-grid">
                    <input type="hidden" name="codigoInventario" id="codigoInventario" value="<%= idInventario %>">
                <div class="frm">
                    <label for="fechaIngreso">Fecha Ingreso</label>
                    <input type="datetime-local" name="fechaIngreso" id="fechaIngreso" required value="<%= fechaIngreso %>">
                </div>
                <div class="frm">
                    <label for="producto">Producto</label>
                    <select name="producto" id="producto" required>
                        <option value="" disabled selected>Seleccione Producto</option>
                        <%
                            // Llenar el select con los productos obtenidos
                            for (T_Productos prod : listaProductos) {
                        %>
                        <option value="<%= prod.getIdProductos() %>" <%= (prod.getProducto().equals(productoSeleccionado)) ? "selected" : "" %>><%= prod.getProducto() %></option>
                        <% 
                            } 
                        %>
                    </select>
                </div>
                <div class="frm">
                    <label for="cantidad">Cantidad</label>
                    <input type="number" name="cantidad" id="cantidad" value="<%= cantidad %>" required>
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
