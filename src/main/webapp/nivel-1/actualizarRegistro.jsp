<%@page import="jakarta.servlet.jsp.tagext.TryCatchFinally"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sagmade.model.*" %>
<%@ page import="com.sagmade.dao.ModuloRegistros" %>
<%@ page import="java.sql.*" %>
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
    <%
        // Obtener las categorías y usuarios desde la base de datos
        ModuloRegistros moduloRegistros = new ModuloRegistros();
        List<T_Areas> listaAreas = moduloRegistros.obtenerAreas();
        List<T_Usuarios> listaUsuarios = moduloRegistros.obtenerUsuarios();
        
        // Obtener el codigo del registro desde la solicitud
        String codigoRegistro = request.getParameter("codigoRegistro");
        
        // Variables para almacenar los datos del registro
        String usuarioId = "";
        String areaId = "";
        String actividadId = "";
        String fechaRegistro = "";
        String descripcion = "";
        String observacion = "";
        
        System.out.println(codigoRegistro);
        
        if (codigoRegistro != null) {
            //Consultar los datos del registro
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            try {
                String url = "jdbc:mysql://localhost:3306/sogepe?useSSL=false"; 
                String usernameDB = "root"; 
                String passwordDB = "sebas1234"; 
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, usernameDB, passwordDB);
                
                String LISTAR_REGISTRO = ("SELECT r.idRegistro AS codigo, r.usuario AS usuarioId, r.fechaRegistro AS fechaRegistro, "
                        + "r.areaTrabajo AS areaId, r.actividad AS actividadId, "
                        + "r.descripcion AS descripcion, "
                        + "r.observacion AS observacion "
                        + "FROM Registro_Informes r "
                        + "WHERE r.idRegistro = ?");
                
                ps = conn.prepareStatement(LISTAR_REGISTRO);
                ps.setString(1, codigoRegistro);
                rs = ps.executeQuery();
                
                if (rs.next()){
                    usuarioId = rs.getString("usuarioId") != null ? rs.getString("usuarioId") : "";
                    areaId = rs.getString("areaId") != null ? rs.getString("areaId") : "";
                    actividadId = rs.getString("actividadId") != null ? rs.getString("actividadId") : "";
                    fechaRegistro = rs.getString("fechaRegistro") != null ? rs.getString("fechaRegistro") : "";
                    descripcion = rs.getString("descripcion") != null ? rs.getString("descripcion") : "";
                    observacion = rs.getString("observacion") != null ? rs.getString("observacion") : "";
                    
                    System.out.print("registro: " + usuarioId + ", " + areaId + ", " + actividadId + ", " + fechaRegistro + ", " + descripcion + ", " + observacion);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    %>
    
    <!-- Scripts -->
    <script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/ckeditor.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        var servletUrl = '${pageContext.request.contextPath}/listarActividades';
        let editors = [];

        $(document).ready(function() {
            let areaSeleccionada = "<%= areaId %>";
            let actividadSeleccionada = "<%= actividadId %>";

            // CKEditor script
            document.querySelectorAll('textarea').forEach((textarea) => {
                ClassicEditor
                    .create(textarea)
                    .then(editor => {
                        editors.push(editor);
                        textarea.removeAttribute('required');
                        editor.setData(textarea.value);
                    })
                    .catch(error => {
                        console.error(error);
                    });
            });

            // Si hay un área seleccionada, cargar actividades
            if (areaSeleccionada) {
                cargarActividades(areaSeleccionada, actividadSeleccionada);
            }

            // Manejar el cambio de área
            $('#area').change(function() {
                let idArea = $(this).val();
                cargarActividades(idArea);
            });

            function cargarActividades(idArea, actividadSeleccionada = "") {
                $.ajax({
                    url: servletUrl,
                    type: 'GET',
                    data: { idArea: idArea },
                    dataType: 'json',
                    success: function(response) {
                        $('#actividad').empty();
                        $('#actividad').append('<option value="" disabled selected>Seleccionar Actividad</option>');

                        $.each(response, function(index, actividad) {
                            $('#actividad').append('<option value="' + actividad.idActividades + '">' + actividad.actividad + '</option>');
                        });

                        if (actividadSeleccionada) {
                            $('#actividad').val(actividadSeleccionada);
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error('Error al obtener actividades:', error);
                    }
                });
            }

            $('form').submit(function(event) {
                event.preventDefault();
                let isValid = true;

                editors.forEach(editor => {
                    const editorData = editor.getData();
                    const textarea = document.getElementById(editor.sourceElement.id);
                    textarea.value = editorData;

                    if (editorData.trim() === '') {
                        isValid = false;
                        editor.sourceElement.classList.add('error');
                    } else {
                        editor.sourceElement.classList.remove('error');
                    }
                });

                if (isValid) {
                    this.submit();
                } else {
                    alert('Por favor, completa todos los campos requeridos.');
                }
            });
        });
    </script>
    
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
            <a href="${pageContext.request.contextPath}/buscarRegistros">
                <img src="../img/volver.png" alt="regresar" height="40px">
            </a>
        </div>
        <form action="">
            <div class="form-grid-r">
                <input type="hidden" name="codigoRegistro" id="codigoRegistro" value="<%= codigoRegistro %>">
                <div class="frm">
                    <label for="username">Usuario</label>
                    <select name="username" id="username">
			            <option value="" disabled selected>Seleccionar Usuario</option>
			            <%
			                for (T_Usuarios user : listaUsuarios) {
			            %>
			            <option value="<%= user.getIdUsuarios() %>" <%= (String.valueOf(user.getIdUsuarios()).equals(usuarioId)) ? "selected" : "" %>><%= user.getUsername() %></option>
			            <% 
			                } 
			            %>
			        </select>
			    </div>
			    <div class="frm">
			        <label for="area">Área de Trabajo</label>
			        <select name="area" id="area">
			            <option value="" disabled selected>Seleccionar Área de Trabajo</option>
			            <%
			                for (T_Areas area : listaAreas) {
			            %>
			            <option value="<%= area.getIdArea() %>" <%= (String.valueOf(area.getIdArea()).equals(areaId)) ? "selected" : "" %>><%= area.getArea() %></option>
			            <% 
			                } 
			            %>
			        </select>
                </div>
                <div class="frm">
                    <label for="actividad">Actividad</label>
                    <select name="actividad" id="actividad">
                        <option value="" disabled selected>Seleccionar Actividad</option>
                    </select>
                </div>
                <div class="frm">
                    <label for="fechaRegistro">Fecha de Registro</label>
                    <input type="datetime-local" name="fechaRegistro" id="fechaRegistro" value="<%= fechaRegistro%>">
                </div>
            </div>
            <div class="textarea">
                <div class="frm-t">
                    <label for="descripcion">Descripcion de Actividad</label>
                    <textarea name="descripcion" id="descripcion" placeholder="Descripción..." required><%= descripcion %></textarea>
                </div>
                <div class="frm-t">
                    <label for="observacion">Ingrese Observacion</label>
                    <textarea name="observacion" id="observacion" placeholder="Observaciones..." required><%= observacion %></textarea>
                </div>
            </div>
            <div class="btn">
                <div class="guardar">
                    <button type="submit" id="siguiente">Guardar Registro</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>