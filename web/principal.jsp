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
        <title>Principal - Instituto Martín Rivero</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/cssPrincipal/styleFonts.css">
        <link rel="stylesheet" type="text/css" href="css/cssPrincipal/style.css">
        <link rel="stylesheet" type="text/css" href="css/cssSlider/styleSlider.css">
        <link href="https://fonts.googleapis.com/css?family=Abel|Mina|Quicksand" rel="stylesheet">

        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="js/jquery.slides.min.js"></script>
        <script src="js/menu.js"></script>
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

        <div id="bienvenida">
            <p class="welcome-short">Bienvenidos al sitio web del Instituto Martín Rivero</p>
            <p class="welcome-large">Bienvenidos al sitio web del Instituto Martín Rivero,
                uno de los centros educativos con más reconocimiento de Ronda.
                Ofrecemos una amplia oferta educativa de educación secundaria,
                bachillerato y formación profesional.</p>
        </div>

        <div class="container-slider">
            <div id="slides">
                <img src="images/slider/example-slide-2.jpg" alt="Enlace a nuestro Blog de Noticias">
                <img src="images/news/daw_entrega_banda.jpg" alt="Visite nuestro apartado de Estudios">
                <img src="images/slider/example-slide-4.jpg" alt="Photo by: Stuart SeegerLink: http://www.flickr.com/photos/stuseeger/97577796/">
                <img src="images/news/entrevista_marcos2.jpg" alt="Bienvenido a la página web del Instituto Martín Rivero">
                <a href="#" class="slidesjs-previous slidesjs-navigation"><i class="icon-chevron-left"></i></a>
                <a href="#" class="slidesjs-next slidesjs-navigation"><i class="icon-chevron-right"></i></a>
            </div>
        </div>



        <section id="noticias">
            <h1>ÚLTIMAS NOTICIAS</h1>
            <c:forEach items="${listaNoticiasPrincipal}" var="noticia">
                <article>
                    <img src="images/news/${noticia.imagen}">
                    <h3>${noticia.titular}</h3>
                    <p>${noticia.subtitulo}</p>
                    <a href="noticias.jsp">Leer más...</a>			
                </article>
            </c:forEach>
        </section>

        <section id="newsletter"> 
            <h1>NEWSLETTER</h1>
            <p>¡Sucríbete a nuestro boletín!</p>
            <p>Te enviaremos un email cada vez que publiquemos una noticia nueva.</p>
            <form action="altaNewsletter">
                <input type="email" placeholder="Dirección de E-mail"/><span class="icon-paper-plane-o"></span><input type="submit" value="Enviar"/>		
            </form>
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


    <script>
        $(function () {
            $('#slides').slidesjs({
                width: 640,
                height: 370,
                navigation: true,
                play: {
                    interval: 8000,
                    auto: true,
                    pauseOnHover: true,
                    restartDelay: 3000
                }
            });
        });
    </script>

</html>
