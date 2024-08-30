<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Platanitos de la Esmeralda</title>
    <link rel="icon" href="img/iconoPestaña.jpg">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="formulario">
        <form action="Validar" method="post">
            <h2>INICIO DE SESIÓN</h2>
            <label for="tipoUsuario">Tipo de Usuario:</label>
            <select class="select_user" name="tipoUsuario" id="tipoUsuario">
                <option value="" disabled selected>Seleccionar Tipo de Usuario</option>
                <option value="Administrador">Administrador</option>
                <option value="Gerente">Gerente</option>
                <option value="Funcionario de Bodega">Funcionario de Bodega</option>
                <option value="Funcionario de Producción">Funcionario de Producción</option>
                <option value="Funcionario de Entrega">Funcionario de Entrega</option>
            </select>
            <label for="usuario">Usuario:</label>
            <input type="text" required id="usuario" name="username" placeholder="Introduzca su Usuario">
            <label for="contraseña">Contraseña:</label>
            <input type="password" required id="contraseña" name="contraseña" placeholder="Introduzca su Contraseña">
            <input type="submit" name="btnIngresar" value="INGRESAR" id="boton">
            <a href="recuperarUsuario.jsp" name="recUsuario" >Olvidé mi usuario</a>
        </form>
        <c:if test="${not empty errorMessage}">
            <p>${errorMessage}</p>
        </c:if>
    </div>

    <!-- Modal personalizado -->
    <div id="myModal" class="modal">
        <div class="modal-content">
 			<c:if test="${not empty errorMessage}">
            	<p>${errorMessage}</p>
        	</c:if>
            <button class="modal-button" id="modal-button">Cerrar</button>
        </div>
    </div>
    
    
   
</body>
</html>