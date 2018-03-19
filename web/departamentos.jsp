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
<c:if test="${!empty param.language}">
    <fmt:setLocale value="${param.language}"/>
    <c:set var="language" scope="session" value="${param.language}"/>
</c:if>
<fmt:setBundle basename="idiomas.recursos"/>
<html>       
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key='titleDepartamentos'/> - Instituto Martín Rivero</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/cssDepartamentos/styleFonts.css">
        <link rel="stylesheet" type="text/css" href="css/cssDepartamentos/style.css">
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
                <form action="departamentos.jsp" method="post">
                    <input type="hidden" name="language" value="en">
                    <input type="image" tabindex="4" src="css/imagenes/british_flag.jpg" alt="Cambiar idioma a Inglés"/>
                </form>

                <form action="departamentos.jsp" method="post">
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
                <a href="#">
                    <button tabindex="6" title="Enlace a la sección Búsqueda">
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


        <section id="departamentos">
            <h1><fmt:message key='titleDepartamentos'/></h1>
            <c:forEach items="${listaAreaDpto}" var="areaDpto">
                <div class="container-areaDpto" id="cad_${areaDpto.codigo}">
                    <h2>${areaDpto.nombre}</h2>               

                    <c:forEach items="${listaDepartamentos}" var="departamento">

                        <c:if test="${departamento.codigoArea.codigo == areaDpto.codigo}">
                            <article id="cd_${departamento.codigo}">
                                <h3>${departamento.nombre}</h3>                        
                                <img src="images/departamentos/${departamento.imagen}">
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
