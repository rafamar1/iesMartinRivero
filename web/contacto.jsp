<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="DAO.*"%>
<%@page import="DTO.*"%>

<!DOCTYPE html>
<c:if test="${!empty param.language}">
    <fmt:setLocale value="${param.language}"/>
    <c:set var="language" scope="session" value="${param.language}"/>
</c:if>
<fmt:setBundle basename="idiomas.recursos"/> 
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contacto - Instituto Martín Rivero</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/cssContacto/styleFonts.css"/>
        <link rel="stylesheet" type="text/css" href="css/cssContacto/style.css"/>
        <link href="https://fonts.googleapis.com/css?family=Abel|Mina|Quicksand" rel="stylesheet"/>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="js/menu.js"></script>

    </head>
    <body>
        <header>
            <a href="principal.jsp"><img src="css/imagenes/logoMartin.gif"/></a>
            <div class="container-banderas">
                <form action="contacto.jsp" method="post">
                    <input type="hidden" name="language" value="en">
                    <input type="image" tabindex="4" src="css/imagenes/british_flag.jpg" alt="Cambiar idioma a Inglés"/>
                </form>

                <form action="contacto.jsp" method="post">
                    <input type="hidden" name="language" value="es">
                    <input type="image" tabindex="3" src="css/imagenes/spain_flag.jpg" alt="Cambiar idioma a Español"/>
                </form>
            </div>
            <h1 tabindex="1" title="Bienvenido a la Web del IES Martín Rivero">IES Martín Rivero</h1>
            <h3 tabindex="2" title="Construimos tu futuro"><fmt:message key='tagline'/></h3>
            <div class="container-botones">
                <a href="http://www.juntadeandalucia.es/averroes/centros-tic/29007962/moodle2/">
                    <button tabindex="5" title="Enlace a la página externa Aula_Virtual"><fmt:message key='btnAula'/></button>
                </a>
                <a href="#"><button tabindex="6" title="Enlace a la sección Búsqueda">
                        <span class="icon-search"></span><fmt:message key='btnBusqueda'/>
                    </button>
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
                    <li><a tabindex="7" title="Enlace a la sección Principal" href="principal.jsp"><span class="icon-home3"></span><fmt:message key='menuInicio'/></a></li>
                    <li><a tabindex="8" title="Enlace a la sección Principal" href="noticias.jsp"><span class="icon-newspaper"></span><fmt:message key='menuNoticias'/></a></li>
                    <li class="submenu">
                        <a tabindex="9" title="Submenú de Cursos" href="#">
                            <span class="icon-study"></span><fmt:message key='menuEstudios'/><span class="caret icon-cheveron-down"></span>
                        </a>
                        <ul class="children">
                            <li><a title="Enlace a la sección Estudios" href="estudios.jsp"><fmt:message key='menuSecundaria'/></a></li>
                            <li><a title="Enlace a la secciónn Estudios" href="estudios.jsp"><fmt:message key='menuBachillerato'/></a></li>
                            <li><a title="Enlace a la sección Estudios" href="estudios.jsp"><fmt:message key='menuFP'/></a></li>
                        </ul>
                    </li>
                    <li class="submenu">
                        <a tabindex="10" title="Submenú de Departamentos" href="#">
                            <span class="icon-briefcase"></span><fmt:message key='menuDepartamentos'/><span class="caret icon-cheveron-down"></span>
                        </a>
                        <ul class="children">
                            <li><a title="Enlace a la sección Lingüistica" href="departamentos.jsp#cad_1"><fmt:message key='menuLinguistica'/></a></li>
                            <li><a title="Enlace a la sección Científico-Tecnologica" href="departamentos.jsp#cad_2"><fmt:message key='menuCiencia'/></a></li>
                            <li><a title="Enlace a la sección Artística" href="departamentos.jsp#cad_3"><fmt:message key='menuArte'/></a></li>
                            <li><a title="Enlace a la sección Familias Profesionales" href="departamentos.jsp#cad_4"><fmt:message key='menuFamiliasPro'/></a></li>
                            <li><a title="Enlace a la sección Otros" href="departamentos.jsp#cad_5"><fmt:message key='menuOtros'/></a></li>
                        </ul>
                    </li>
                    <li><a tabindex="11" title="Enlace a la sección contacto" href="contacto.jsp"><span class="icon-envelop"></span><fmt:message key='menuContacto'/></a></li>
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

            <form id="form1" action="Mail" method="post">
                <h3>ENVÍANOS UNA CONSULTA</h3>
                <label for="name">Nombre: <span class="requerido">*</span></label><br>
                <input tabindex="16" type="text" id="name" name="nombre" placeholder="Introduzca su nombre" required/>
                <br>
                <label for="destinatario">Destinatario: <span class="requerido">*</span></label><br>
                <input tabindex="17" aria-required="true" type="email" id="destinatario" name="destinatario" placeholder="Introduzca su dirección de correo" required />
                <br>  
                <label for="asunto">Asunto <span class="requerido">*</span></label><br>
                <input tabindex="18" aria-required="true" type="text" id="asunto" name="asunto" placeholder="Escriba aquí el asunto" required />
                <br>             
                <label for="mensaje">Mensaje: <span class="requerido">*</span></label><br>
                <textarea tabindex="19" id="mensaje" name="mensaje" placeholder="Escriba aquí un mensaje..."></textarea>
                <br>

                <input tabindex="20" type="submit" value="Enviar" id="submit-button" />

                <p id="required"><span class="requerido">*</span> rellenar obligatoriamente</p>

                <c:if test="${not empty resultado}">
                    <p id="respuesta"><span><%=request.getAttribute("resultado")%></span></p>
                        </c:if>

            </form>    
        </section>

        <section id="localización">
            <h1>LOCALIZACIÓN</h1>
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2688.212477524771!2d-5.168841812822875!3d36.750377685945715!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd0d31360b6956b1%3A0x150f35b20253d4b8!2sIES+Mart%C3%ADn+Rivero!5e0!3m2!1ses!2ses!4v1521387254320" width="500" height="350" frameborder="0" style="border:0" allowfullscreen></iframe>
        </section>


        <section id="newsletter"> 
            <h1>NEWSLETTER</h1>
            <p><fmt:message key='newsletter1'/></p>
            <p><fmt:message key='newsletter2'/></p>
            <form action="#">
                <input type="email" placeholder="<fmt:message key='email'/>"/><input type="submit" value="Enviar"/>		
            </form>
        </section>    
        <footer>
            <div class="footer-container">
                <div class="footer-main">
                    <div class="footer-columna">
                        <h3><fmt:message key='datosCentro'/></h3>
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