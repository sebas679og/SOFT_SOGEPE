<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Admin</title>
    <link rel="icon" href="../img/iconoPestaña.jpg" type="image/jpeg">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/Menu.css">
</head>
<body>
    <header class="">
        <div class="header">
            <a href="menu_principalAdmin.jsp">
                <img src="../img/Letras.png" alt="Platanitos de La Esmeralda" width="250px" class="logo">
            </a>
        </div>
        <div class="icons">
            <img src="../img/user.png" alt="User" height="35px" class="icon">
            <img src="../img/menu.png" alt="menu" height="35px" class="icon">
        </div>
    </header>
   
    <div class="contenido">
        <nav class="navigation">
            <div> 
                <a href="gestionUsuarios.jsp" class="btn-icon">
                    <img src="../img/GestionUser.png" height="120px" alt="Usuarios">
                    <span>Gestion de Usuarios</span>
                </a>
            </div>
            <div>
                <a href="#" class="btn-icon">
                    <img src="../img/gestionAct.png" height="120px" alt="Actividades">
                    <span>Gestion de Actividades</span>
                </a>
            </div>
            <div>
                <a href="#" class="btn-icon">
                    <img src="../img/inventario.png" height="120px" alt="Inventario">
                    <span>Gestion de Inventario</span>
                </a>
            </div>
        </nav>
    </div>
</body>
</html>
