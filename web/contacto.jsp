<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="DAO.*"%>
<%@page import="DTO.*"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contacto - Instituto Martín Rivero</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/cssContacto/styleFonts.css"/>
        <link rel="stylesheet" type="text/css" href="css/cssContacto/style.css"/>
        <link href="https://fonts.googleapis.com/css?family=Abel|Mina|Quicksand" rel="stylesheet"/>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="js/jquery.slides.min.js"></script>
        <script src="js/menu.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
        <script src="js/localizacion.js"></script>
    </head>
    <body>
        <header>
            <img src="css/imagenes/logoMartin.gif" alt="Logo del Instituto I.E.S Martín Rivero"/>
            <div class="container-banderas">
                <img tabindex="3" src="css/imagenes/spain_flag.jpg" alt="Cambiar idioma a Español"/>
                <img tabindex="4" src="css/imagenes/british_flag.jpg" alt="Cambiar idioma a inglés"/>
            </div>
            <h1 tabindex="1" title="Bienvenido a la Web del IES Martín Rivero">IES Martín Rivero</h1>
            <h3 tabindex="2" title="Eslogan">Creamos tu futuro</h3>
            <div class="container-botones">
                <a title="Enlace a la página externa Aula_Virtual" href="http://www.juntadeandalucia.es/averroes/centros-tic/29007962/moodle2/">
                    <button tabindex="5">Aula Virtual</button></a>
                <a title="Enlace a la sección Búsqueda" href="#">
                    <button tabindex="5"><span class="icon-search"></span>Busqueda</button>
                </a>
            </div>
        </header>
        
        <div id="menu-principal">
            <div class="menu_bar">
                <a title="Abrir Menú Desplegable" href="#" class="boton_menu" id="bars"><span class="icon-menu"></span></a>
                <a title="Cerrar el Menú Desplegable" href="#" class="boton_menu" id="cerrar"><span class="icon-cross"></span>MENU</a>
            </div>

            <nav>
                <ul>
                    <li><a tabindex="7" title="Enlace a la sección Principal" href="principal.jsp"><span class="icon-home3"></span>Inicio</a></li>
                    <li><a tabindex="8" title="Enlace a la sección Principal" href="noticias.jsp"><span class="icon-newspaper"></span>Noticias</a></li>
                    <li class="submenu">
                        <a tabindex="9" title="Submenú de Cursos" href="#">
                            <span class="icon-study"></span>Estudios<span class="caret icon-cheveron-down"></span>
                        </a>
                        <ul class="children">
                            <li><a title="Enlace a la sección Estudios" href="estudios.jsp">Secundaria</a></li>
                            <li><a title="Enlace a la secciónn Estudios" href="estudios.jsp">Bachillerato</a></li>
                            <li><a title="Enlace a la sección Estudios" href="estudios.jsp">Formacion Profesional</a></li>
                        </ul>
                    </li>
                    <li class="submenu">
                        <a tabindex="10" title="Submenú de Departamentos" href="#">
                            <span class="icon-briefcase"></span>Departamentos<span class="caret icon-cheveron-down"></span>
                        </a>
                        <ul class="children">
                            <li><a title="Enlace a la sección Departamentos" href="departamentos.jsp">Lingüistica</a></li>
                            <li><a title="Enlace a la sección Departamentos" href="departamentos.jsp">Científico-Tecnologica</a></li>
                            <li><a title="Enlace a la sección Departamentos" href="departamentos.jsp">Artística</a></li>
                            <li><a title="Enlace a la sección Departamentos" href="departamentos.jsp">Familias Profesionales</a></li>
                        </ul>
                    </li>
                    <li><a tabindex="11" title="Enlace a la sección contacto" href="contacto.jsp"><span class="icon-envelop"></span>Contacto</a></li>
                </ul>
            </nav>		
        </div>

        <section id="contacto">
            <h1>CONTACTO</h1>
            <p>Bienvenido a la web del IES Martín Rivero.  No dejes de utilizar este formulario para resolver tus dudas. También tienes a tu disposicion los numeros de Secretaría que te atenderan en horario de  9.00 a 14.00</p>
            <p tabindex="12" title="Dirección"><span class="icon-location"></span> Calle Fernando de los Ríos, 1, 29400 Ronda</p>
            <p tabindex="13" title="Teléfono"><span class="icon-phone"></span> (+34)952 16 99 07</p>
            <p tabindex="14" title="Fax"><span class="icon-fax"></span> (+34)952 16 99 13</p>
            <p tabindex="15" title="E-mail"><span class="icon-envelop"></span> info@iesmartinrivero.com</p>     
        </section>    
        <section id="consulta">

            <form id="form1">
                <h3>ENVÍENOS UNA CONSULTA</h3>
                <label for="name">Nombre: <span class="requerido">*</span></label><br>
                <input tabindex="16" aria-required="true" type="text" id="name" placeholder="Introduzca su nombre" required/>
                <br>
                <label for="email">Dirección de correo: <span class="requerido">*</span></label><br>
                <input tabindex="17" aria-required="true" type="email" id="email" placeholder="Introduzca su dirección de correo" required />
                <br>   
                <label for="asunto">Asunto: <span class="requerido">*</span></label><br>
                <select tabindex="18" id="asunto">
                    <option value="opcion1">Plazos de Admisión y Matrícula</option>
                    <option value="opcion2">Actividades Extraescolares y Complemetarias</option>
                    <option value="opcion3">Área Pasem</option>
                    <option value="opcion3">Otros</option>
                </select>                
                <br>             
                <label for="mensaje">Mensaje: <span class="requerido">*</span></label><br>
                <textarea tabindex="19" id="mensaje" placeholder="Escriba aquí su mensaje..."></textarea>
                <br>

                <input tabindex="20" type="submit" value="Enviar" id="submit-button" />

                <p id="required"><span class="requerido">*</span> rellenar obligatoriamente</p>
            </form>    
        </section>

        <section id="localización">
            <h1>LOCALIZACIÓN</h1>
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2688.212477524771!2d-5.168841812822875!3d36.750377685945715!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd0d31360b6956b1%3A0x150f35b20253d4b8!2sIES+Mart%C3%ADn+Rivero!5e0!3m2!1ses!2ses!4v1521387254320" width="500" height="350" frameborder="0" style="border:0" allowfullscreen></iframe>
        </section>


        <section id="newsletter"> 
            <h1>NEWSLETTER</h1>
            <p>¡Sucríbete a nuestro boletín!</p>
            <p>Te enviaremos un email cada vez que publiquemos una noticia nueva.</p>
            <form action="altaNewsletter">
                <input tabindex="15" type="email" placeholder="Dirección de E-mail"/><span class="icon-paper-plane-o"></span><input tabindex="16" type="submit" value="Enviar"/>		
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
                        <img src="css/imagenes/educaMinisterio.png" alt="Logo gobierno de España"/>
                    </div>
                    <div class="footer-columna">
                        <img src="css/imagenes/educaJunta.png" alt="Logo junta de andalucia"/>
                    </div>
                </div>
                <p>&copy; 2018 I.E.S. Martín Rivero</p>					
            </div>
        </footer>
    </body>
</html>