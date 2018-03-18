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


<html>       
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Administrador - IES Martín Rivero</title>
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
        </header>

        <div id="container-admin">        

            <h2>Acceso al Modo Administrador</h2>
            <jsp:include page="CompruebaLogin"/>
            <c:choose>
                <c:when test="${!sessionScope.loginOk}">
                    <c:if test="${requestScope.passFail}">
                        <p style="color:red;text-align:center;">Usuario o contrase&ntilde;a invalidos</p>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <jsp:forward page="/administrador.jsp"/>
                </c:otherwise>
            </c:choose>

            <form action="login.jsp" method="post">
                <p> <label for="usuario">Usuario</label>
                    <input type="text" name="usuario"/></p>
                <p><label for="password">Contraseña</label>
                    <input type="password" name="password"/></p>
                <input type="submit" value="Entrar" />
            </form>

        </div>
    </body>


</html>
