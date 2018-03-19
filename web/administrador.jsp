<%-- 
    Document   : index
    Created on : 12-mar-2018, 0:03:51
    Author     : RafaMar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="DAO.*"%>
<%@page import="DTO.*"%>

<!DOCTYPE html>
<c:if test="${!sessionScope.loginOk}">
    <jsp:forward page="/login.jsp"/>
</c:if>
<jsp:include page="/CargaDatos"/>
<html>       
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modo Administrador - IES Martín Rivero</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/cssPrincipal/styleFonts.css">
        <link rel="stylesheet" type="text/css" href="css/cssPrincipal/style.css">
        <link href="https://fonts.googleapis.com/css?family=Abel|Mina|Quicksand" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-latest.js"></script>
    </head>

    <body>


        <header>
            <a href="principal.jsp"><img src="css/imagenes/logoMartin.gif"/></a>
            <div class="container-banderas">
                <img src="css/imagenes/spain_flag.jpg"/>
                <img src="css/imagenes/british_flag.jpg"/>
            </div>
            <h1>IES Martín Rivero</h1>
            <h3>Creamos tu futuro</h3>
            <form action='Logout' method='POST' class="logout"> 
                <input type='image' src='css/imagenes/logout.png' title='Cerrar Sesion'>
            </form>            
        </header>



        <div id="container-admin">    

            <h2>Bienvenido al Modo Administrador</h2>
            <h3>Ahora puedes dar de alta noticias en la Base de datos</h3>
            <c:if test="${sessionScope.altaNoticia != null}">
                <p style="text-align: center;color: #105e10;">${sessionScope.altaNoticia}</p>
            </c:if>
            <form action="AltaNews" method="post" enctype="multipart/form-data">
                <p> 
                    <label for="titular">Titular</label>
                    <input type="text" name="titular" required/>
                </p>
                <p>
                    <label for="subtitulo">Subtitulo</label>
                    <input type="text" name="subtitulo" required/>
                </p>
                <p>
                    <label for="imagen">Imagen</label>
                    <input type="file" name="imagen" required/>
                </p>
                <p>
                    <label for="Departamento">Departamento</label>
                    <select name="departamento" required>
                        <c:forEach var="departamento" items="${listaDepartamentos}">
                            <option value="${departamento.codigo}">${departamento.nombre}</option>
                        </c:forEach>
                    </select> 
                </p>
                <p>Cuerpo de la noticia </p>
                <p><textarea maxlength="5000" rows="8" name="descripcion" required></textarea></p>

                <input type="submit" value="Crear Noticia" /><input type="reset" value="Limpiar"/>
            </form>

        </div>
    </body>
</html>
