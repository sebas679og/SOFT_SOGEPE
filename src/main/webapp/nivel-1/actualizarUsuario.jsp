<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%
    // Conexión a la base de datos
    String url = "jdbc:mysql://localhost:3306/sogepe?useSSL=false"; // Cambia esto por tu URL de base de datos
    String usernameDB = "root"; // Cambia esto por tu usuario de la base de datos
    String passwordDB = "sebas1234"; // Cambia esto por tu contraseña de la base de datos
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    // Obtener el ID del usuario a actualizar de la solicitud
    String userId = request.getParameter("id"); // El ID debería ser pasado como parámetro en la URL o desde otro formulario

    // Variables para almacenar los datos del usuario
    int tipoDocumento = 0;
    int numeroIdentificacion = 0;
    String primerNombre = "";
    String segundoNombre = "";
    String primerApellido = "";
    String segundoApellido = "";
    String telefono = "";
    String direccion = "";
    int genero = 0;
    String username = "";
    String contraseña = "";
    String correo = "";
    int estadoUsuario = 0;
    int rol = 0;
    int idPersonas = 0;
    int idUsuarios = 0;

    try {
        // Establecer conexión con la base de datos
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, usernameDB, passwordDB);

        // Consultar los datos del usuario y de la persona existente
        String sql = "SELECT u.username, u.contraseña, u.correo, u.estadoUsuario, u.rol, p.tipoDocumento, p.numeroIdentificacion, p.primerNombre, " +
        			"p.segundoNombre, p.primerApellido, p.segundoApellido, p.telefono, p.direccion, p.genero, p.idPersonas, u.idUsuarios " +
        			"FROM usuarios u " +
        			"JOIN personas p ON u.persona = p.idPersonas " +
        			"WHERE u.idUsuarios = ?";
        
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, userId);
        rs = stmt.executeQuery();

        // Si el usuario existe, obtener sus datos
        if (rs.next()) {
            tipoDocumento = rs.getInt("tipoDocumento");
            numeroIdentificacion = rs.getInt("numeroIdentificacion");
            primerNombre = rs.getString("primerNombre");
            segundoNombre = rs.getString("segundoNombre");
            primerApellido = rs.getString("primerApellido");
            segundoApellido = rs.getString("segundoApellido");
            telefono = rs.getString("telefono");
            direccion = rs.getString("direccion");
            genero = rs.getInt("genero");
            username = rs.getString("username");
            contraseña = rs.getString("contraseña");
            correo = rs.getString("correo");
            estadoUsuario = rs.getInt("estadoUsuario");
            rol = rs.getInt("rol");
            idPersonas = rs.getInt("idPersonas");
            idUsuarios = rs.getInt("idUsuarios");
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al cargar datos: ");
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actualizar Usuario</title>
    <link rel="icon" href="../img/iconoPestaña.jpg" type="image/jpeg">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/Menu.css">
    <link rel="stylesheet" href="../css/formPersona.css">
</head>
<body>
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
        <h1 class="tituloFormUser">Formulario de Actualización de Usuario</h1>
        <div class="cerrar">
            <a href="${pageContext.request.contextPath}/buscarUsuarios">
                <img src="../img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="${pageContext.request.contextPath}/actualizarUsuario" method="POST">
            <!-- Campo oculto para enviar el ID del usuario -->
            <input type="hidden" name="idUsuarios" value="<%= idUsuarios %>">
            <input type="hidden" name="idPersonas" value="<%= idPersonas %>">

            <div class="form-grid">
                <div class="frm">
                    <label for="tipoDocumento">Tipo de Documento</label>
                    <select class="select" name="tipoDocumento" id="tipoDocumento" required>
                        <option value="" disabled selected>Seleccione Tipo de Identificación</option>
                        <option value="1" <%= tipoDocumento == 1 ? "selected" : "" %>>Cédula de Ciudadanía - CC</option>
                        <option value="2" <%= tipoDocumento == 2 ? "selected" : "" %>>Cédula de Extranjería - CE</option>
                        <option value="3" <%= tipoDocumento == 3 ? "selected" : "" %>>Permiso Especial de Permanencia - PEP</option>
                        <option value="4" <%= tipoDocumento == 4 ? "selected" : "" %>>Pasaporte - PAS</option>
                        <option value="5" <%= tipoDocumento == 5 ? "selected" : "" %>>Número de Identificación de Extranjeros - NIE</option>
                    </select>
                </div>
                <div class="frm">
                    <label for="numeroIdentificacion">Número de Identificación</label>
                    <input type="number" name="numeroIdentificacion" id="numeroIdentificacion" value="<%= numeroIdentificacion %>" required>
                </div>
                <div class="frm">
                    <label for="primerNombre">Primer Nombre</label>
                    <input type="text" name="primerNombre" id="primerNombre" value="<%= primerNombre %>" required>
                </div>
                <div class="frm">
                    <label for="segundoNombre">Segundo Nombre</label>
                    <input type="text" name="segundoNombre" id="segundoNombre" value="<%= segundoNombre %>">
                </div>
                <div class="frm">
                    <label for="primerApellido">Primer Apellido</label>
                    <input type="text" name="primerApellido" id="primerApellido" value="<%= primerApellido %>" required>
                </div>
                <div class="frm">
                    <label for="segundoApellido">Segundo Apellido</label>
                    <input type="text" name="segundoApellido" id="segundoApellido" value="<%= segundoApellido %>" required>
                </div>
                <div class="frm">
                    <label for="telefono">Teléfono</label>
                    <input type="tel" name="telefono" id="telefono" value="<%= telefono %>" required>
                </div>
                <div class="frm">
                    <label for="direccion">Dirección</label>
                    <input type="text" name="direccion" id="direccion" value="<%= direccion %>" required>
                </div>
                <div class="frm">
                    <label for="genero">Género</label>
                    <select class="select" name="genero" id="genero" required>
                        <option value="" disabled selected>Seleccione Género</option>
                        <option value="1" <%= genero == 1 ? "selected" : "" %>>Femenino</option>
                        <option value="2" <%= genero == 2 ? "selected" : "" %>>Masculino</option>
                    </select>
                </div>
                <div class="frm">
                    <label for="rol">Rol de Usuario</label>
                    <select class="select" name="rol" id="rol" required>
                        <option value="" disabled selected>Seleccione Rol</option>
                        <option value="1" <%= rol == 1 ? "selected" : "" %>>Administrador</option>
                        <option value="2" <%= rol == 2 ? "selected" : "" %>>Gerente</option>
                        <option value="4" <%= rol == 4 ? "selected" : "" %>>Funcionario de Producción</option>
                        <option value="5" <%= rol == 5 ? "selected" : "" %>>Funcionario de Entrega</option>
                        <option value="6" <%= rol == 6 ? "selected" : "" %>>Funcionario de Bodega</option>
                    </select>
                </div>
                <div class="frm">
                    <label for="username">Nombre de Usuario</label>
                    <input type="text" name="username" id="username" value="<%= username %>" required>
                </div>
                <div class="frm">
                    <label for="contraseña">Contraseña</label>
                    <input type="password" name="contraseña" id="contraseña" value="<%= contraseña %>" required>
                </div>
                <div class="frm">
                    <label for="correo">Correo Electrónico</label>
                    <input type="email" name="correo" id="correo" value="<%= correo %>" required>
                </div>
                <div class="frm">
                    <label for="estadoUsuario" disabled selected>Estado del Usuario</label>
                    <select class="select" name="estadoUsuario" id="estadoUsuario" required>
                    	<option value="" disabled selected>Seleccione Estado</option>
                        <option value="1" <%= estadoUsuario == 1 ? "selected" : "" %>>Activo</option>
                        <option value="2" <%= estadoUsuario == 2 ? "selected" : "" %>>Inactivo</option>
                        <option value="3" <%= estadoUsuario == 3 ? "selected" : "" %>>Suspendido</option>
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
