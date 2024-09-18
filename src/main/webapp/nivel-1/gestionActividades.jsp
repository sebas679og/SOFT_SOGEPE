<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-co">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro de Inventario</title>
        <link rel="icon" href="../img/iconoPestaña.jpg" type="image/jpeg">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/Menu.css">
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
       
        <div class="contenidoP">
            <h1>RESGISTRO Y CONSULTA DE INFORMES</h1>
            <div class="cerrar">
                <a href="menu_principalAdmin.jsp">
                    <img src="../img/cerrar.png" alt="cerrar" height="20px">
                </a>
            </div>
            <div class="contenido">
                <nav class="navigation">
                    <div> 
                        <a href="registroActividad.jsp" class="btn-icon-u">
                            <img src="../img/informe.png" height="120px" alt="Agregar a Inventario">
                            <span>Realizar Informe</span>
                        </a>
                    </div>
                    <div> 
                        <a href="/SOFT_SOGEPE/buscarRegistros" class="btn-icon-u">
                            <img src="../img/buscarInforme.png" height="120px" alt="Productos">
                            <span>Consultar Informes</span>
                        </a>
                    </div>
                </nav>
            </div>
        </div>
    </body>
</html>