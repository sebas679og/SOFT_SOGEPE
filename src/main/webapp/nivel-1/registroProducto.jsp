<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <h1 class="tituloFormUser">Registrar Producto</h1>
        <div class="cerrar">
            <a href="gestionInventario.jsp">
                <img src="../img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="">
            <div class="form-grid">
                <div class="frm">
                    <label for="categoria">Categoria</label>
                    <select name="categoria" id="categoria">
                        <option value="" disabled selected>Seleccionar Categoria</option>
                        <option value="">valores</option>
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
                        <button onclick="">Eliminar Producto</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</body>
</html>