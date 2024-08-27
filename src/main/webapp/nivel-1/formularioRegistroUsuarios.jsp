<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Regsitro Usuario</title>
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
        <h1 class="tituloFormUser">Formulario de Registro Usuario</h1>
        <div class="cerrar">
            <a href="gestionUsuarios.jsp">
                <img src="../img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="/SOFT_SOGEPE/insertarpu" method="POST">
            <div class="form-grid">
                <div class="frm">
                    <label for="tipoDocumento">Tipo de Documento</label>
                    <select class="select" name="tipoDocumento" id="tipoDocumento" required>
                        <option value="">Seleccione Tipo de Identificacion</option>
                        <option value="1">Cedula de Ciudadania - CC</option>
                        <option value="2">Cedula de Extranjeria - CE</option>
                        <option value="3">Permiso Especial de Permanencia - PEP</option>
                        <option value="4">Pasaporte - PAS</option>
                        <option value="5">Numero de Identificacion de Extranjeros - NIE</option>
                    </select>
                </div>
                <div class="frm">
                    <label for="numeroIdentificacion">Número de Identificacion</label>
                    <input type="number" name="numeroIdentificacion" id="numeroIdentificacion" required>
                </div>
                <div class="frm">
                    <label for="primerNombre">Primer Nombre</label>
                    <input type="text" name="primerNombre" id="primerNombre" required>
                </div>
                <div class="frm">
                    <label for="segundoNombre">Segundo Nombre</label>
                    <input type="text" name="segundoNombre" id="segundoNombre">
                </div>
                <div class="frm">
                    <label for="primerApellido">Primer Apellido</label>
                    <input type="text" name="primerApellido" id="primerApellido" required>
                </div>
                <div class="frm">
                    <label for="segundoApellido">Segundo Apellido</label>
                    <input type="text" name="segundoApellido" id="segundoApellido" required>
                </div>
                <div class="frm">
                    <label for="telefono">Telefono</label>
                    <input type="tel" name="telefono" id="telefono" required>
                </div>
                <div class="frm">
                    <label for="direccion">Dirección</label>
                    <input type="text" name="direccion" id="direccion" required>
                </div>
                <div class="frm">
                    <label for="genero">Genero</label>
                    <select class="select" name="genero" id="genero" required>
                        <option value="">Seleccione Genero</option>
                        <option value="1">Femenino</option>
                        <option value="2">Masculino</option>
                    </select>
                </div>
                <div class="frm">
                    <label for="rol">Rol de Usuario</label>
                    <select class="select" name="rol" id="rol" required>
                        <option value="">Seleccione Rol</option>
                        <option value="1">Administrador</option>
                        <option value="2">Gerente</option>
                        <option value="4">Funcionario de Producción</option>
                        <option value="5">Funcionario de Entrega</option>
                        <option value="6">Funcionario de Bodega</option>
                        
                    </select>
                </div>
                <div class="frm">
                    <label for="username">Usuario</label>
                    <input type="text" name="username" id="username" required>
                </div>
                <div class="frm">
                    <label for="contraseña">Contraseña</label>
                    <input type="text" name="contraseña" id="contraseña" required>
                </div>
                <div class="frm">
                    <label for="correo">Correo</label>
                    <input type="text" name="correo" id="correo" required>
                </div>
                <div class="frm">
                    <label for="estadoUsuario">Estado del Usuario</label>
                    <select class="select" name="estadoUsuario" id="estadoUsuario" required>
                        <option value="">Seleccione Estado</option>
                        <option value="1">Activo</option>
                        <option value="2">Inactivo</option>
                        <option value="3">Suspendido</option>
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