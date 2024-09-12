<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Actividad</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/Menu.css">
    <link rel="stylesheet" href="../css/formPersona.css">
    <link rel="stylesheet" href="../css/tablaProd.css">
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
        <h1 class="tituloFormUser">Registrar Informe de Actividad</h1>
        <div class="cerrar">
            <a href="consultarRegistros.jsp">
                <img src="../img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="">
            <div class="form-grid-r">
                <div class="frm">
                    <label for="username">Usuario</label>
                    <select name="username" id="username">
                        <option value="" disabled selected>Seleccionar Usuario</option>
                        <option value="">valores</option>
                    </select>
                </div>
                <div class="frm">
                    <label for="area">Área de Trabajo</label>
                    <select name="area" id="area">
                        <option value="" disabled selected>Seleccionar Área de Trabajo</option>
                        <option value="">valores</option>
                    </select>
                </div>
                <div class="frm">
                    <label for="actividad">Actividad</label>
                    <select name="actividad" id="actividad">
                        <option value="" disabled selected>Seleccionar Actividad</option>
                        <option value="">valores</option>
                    </select>
                </div>
                <div class="frm">
                    <label for="fechaRegistro">Fecha de Registro</label>
                    <input type="datetime-local" name="fechaRegistro" id="fechaRegistro">
                </div>
            </div>
            <div class="textarea">
                <div class="frm-t">
                    <label for="descripcion">Descripcion de Actividad</label>
                    <textarea name="descripcion" id="descripcion" placeholder="Descripción..." required></textarea>
                </div>
                <div class="frm-t">
                    <label for="observacion">Ingrese Observacion</label>
                    <textarea name="observacion" id="observacion" placeholder="Observaciones..." required></textarea>
                </div>
            </div>
            <div class="btn">
                <div class="guardar">
                    <button type="submit" id="siguiente">Guardar Registro</button>
                </div>
            </div>
        </form>
    </div>

    <script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/ckeditor.js"></script>

    <script>
        // Selecciona todos los elementos textarea y aplica CKEditor a cada uno
        document.querySelectorAll('textarea').forEach((textarea) => {
          ClassicEditor
            .create(textarea)
            .catch(error => {
              console.error(error);
            });
        });
    </script>
</body>
</html>