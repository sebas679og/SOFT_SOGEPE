<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-co">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - Aplicación</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <!-- Aquí puedes agregar más enlaces a tus hojas de estilo -->
    <style type="text/css">
    	/* Estilo para el contenedor principal */
		.container {
		    height: auto;
		    width: 1300px;
		    text-align: center;
		    align-items: center;
		    background-color: #d1d8d4;
		    border-radius: 8px;
		    padding: 20px;
		    margin: 20px;
		    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
		    position: absolute;
		    top: 120px;
		    justify-content: center;/* Incluye el padding dentro del tamaño del elemento */
		    opacity: 95%; /* Color de fondo suave */
		}
		
		/* Estilo para el header */
		header h1 {
		    margin-bottom: 20px; /* Añade un espacio debajo del título */
		    font-size: 2em; /* Tamaño de fuente más grande para el título */
		    color: #333; /* Color del texto del título */
		}
		
		/* Estilo para el section */
		section p {
		    color: red; /* Color del texto para el mensaje de error */
		    font-weight: bold; /* Negrita para el mensaje de error */
		    margin-bottom: 20px; /* Espacio debajo del mensaje de error */
		}
		
		/* Estilo para el footer */
		footer p {
		    margin-top: 20px; /* Añade un espacio encima del enlace */
		}
		
		footer a {
		    text-decoration: none; /* Elimina el subrayado del enlace */
		    color: #007bff; /* Color del enlace */
		    font-weight: bold; /* Negrita para el enlace */
		}
		
		footer a:hover {
		    text-decoration: underline; /* Subraya el enlace al pasar el cursor */
		}
    		
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>Error en la Aplicación</h1>
        </header>
        <section>
            <p style="color: red; font-weight: bold;">
                <c:choose>
                    <c:when test="${not empty errorMessage}">
                        ${errorMessage}
                    </c:when>
                    <c:otherwise>
                        Ha ocurrido un error inesperado. Por favor, intenta nuevamente más tarde.
                    </c:otherwise>
                </c:choose>
            </p>
        </section>
        <img alt="Error" src="${pageContext.request.contextPath}/img/advertencia.png" height="200px">
        <footer>
            <p><a href="${pageContext.request.contextPath}/nivel-1/menu_principalAdmin.jsp">Volver al menú principal</a></p>
        </footer>
    </div>
</body>
</html>
