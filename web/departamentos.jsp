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

<jsp:include page="/CargaDatos"/>   
<html>       
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Departamentos - Instituto Martín Rivero</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/cssDepartamentos/styleFonts.css">
        <link rel="stylesheet" type="text/css" href="css/cssDepartamentos/style.css">

        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="js/jquery.slides.min.js"></script>
        <script src="js/menu.js"></script>
    </head>

    <body>

        <header>
            <img src="css/imagenes/logoMartin.gif"/>
            <div class="container-banderas">
                <img src="css/imagenes/spain_flag.jpg"/>
                <img src="css/imagenes/british_flag.jpg"/>
            </div>
            <h1>IES Martín Rivero</h1>
            <h3>Creamos tu futuro</h3>
            <div class="container-botones">
                <a href="http://www.juntadeandalucia.es/averroes/centros-tic/29007962/moodle2/"><button>Aula Virtual</button></a>
                <a href="#"><button><span class="icon-search"></span>Busqueda</button></a>
            </div>
        </header>

        <div id="menu-principal">
            <div class="menu_bar">
                <a href="#" class="boton_menu" id="bars"><span class="icon-menu"></span></a>
                <a href="#" class="boton_menu" id="cerrar"><span class="icon-cross"></span>MENU</a>
            </div>

            <nav>
                <ul>
                    <li><a href="principal.jsp"><span class="icon-home3"></span>Inicio</a></li>
                    <li><a href="noticias.jsp"><span class="icon-newspaper"></span>Noticias</a></li>
                    <li class="submenu">
                        <a href="#">
                            <span class="icon-study"></span>Estudios<span class="caret icon-cheveron-down"></span>
                        </a>
                        <ul class="children">
                            <li><a href="#paginaEstudios">Secundaria</a></li>
                            <li><a href="#paginaEstudios">Bachillerato</a></li>
                            <li><a href="#paginaEstudios">Formacion Profesional</a></li>
                        </ul>
                    </li>
                    <li class="submenu">
                        <a href="#">
                            <span class="icon-briefcase"></span>Departamentos<span class="caret icon-cheveron-down"></span>
                        </a>
                        <ul class="children">
                            <li><a href="departamentos.jsp">Lingüistica</a></li>
                            <li><a href="departamentos.jsp">Científico-Tecnologica</a></li>
                            <li><a href="departamentos.jsp">Artística</a></li>
                            <li><a href="departamentos.jsp">Familias Profesionales</a></li>
                        </ul>
                    </li>
                    <li><a href="#"><span class="icon-envelop"></span>Contacto</a></li>
                </ul>
            </nav>		
        </div>


        <section id="departamentos">
            <h1>DEPARTAMENTOS</h1>
            <c:forEach items="${listaAreaDpto}" var="areaDpto">
                <div class="container-areaDpto" >
                <h2>${areaDpto.nombre}</h2>               
                
                <c:forEach items="${listaDepartamentos}" var="departamento">
                    
                    <c:if test="${departamento.codigoArea.codigo == areaDpto.codigo}">
                       <article>
                        <h3>${departamento.nombre}</h3>                        
                        <img src="images/departamentos/Lengua.png">
                        <p>${departamento.descripcion}</p>			
                    </article>
                    </c:if>
                </c:forEach>
                    </div>
            </c:forEach>
        </section>


        <footer>
            <div class="footer-container">
                <div class="footer-main">
                    <div class="footer-columna">
                        <h3>Datos del Centro</h3>
                        <p><span class="icon-location"></span>Calle Fernando de los Ríos, 1, 29400 Ronda</p>
                        <p><span class="icon-phone"></span>(+34)952 16 99 07</p>
                        <p><span class="icon-fax"></span>(+34)952 16 99 13</p>
                        <p><span class="icon-envelop"></span>info@iesmartinrivero.com</p>
                    </div>
                    <div class="footer-columna">
                        <img src="css/imagenes/educaMinisterio.png"/>
                    </div>
                    <div class="footer-columna">
                        <img src="css/imagenes/educaJunta.png"/>
                    </div>
                </div>
                <p>&copy; 2018 I.E.S. Martín Rivero</p>					
            </div>
        </footer>
    </body>
</html>
