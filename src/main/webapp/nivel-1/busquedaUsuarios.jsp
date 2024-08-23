<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Regsitro de Usuarios</title>
    <link rel="icon" href="../img/iconoPestaña.jpg" type="image/jpeg">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/Menu.css">
    <link rel="stylesheet" href="../css/buscarUsers.css">
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

    <div class="Persona1">
        <h1 class="tituloFormUser">Consulta de Usuarios</h1>
        <div class="cerrar">
            <a href="gestionUsuarios.jsp">
                <img src="../img/volver.png" alt="regresar" height="40px">
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
                        <th>Número de Identificacion</th>
                        <th>Rol</th>
                        <th>Estado del Usuario</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>CC</td>
                        <td>1234567890</td>
                        <td>Administrador</td>
                        <td>Activo</td>
                        <td>
                            <button class="action-btn edit-btn">Actualizar</button>
                            <button class="action-btn delete-btn">Eliminar</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>