

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
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Web Instituto</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
        <link rel="stylesheet" type="text/css" href="css/cssEstudios/styleFonts.css"/>
        <link rel="stylesheet" type="text/css" href="css/cssEstudios/style.css"/>
        <link href="https://fonts.googleapis.com/css?family=Abel|Mina|Quicksand" rel="stylesheet"/>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="js/menu.js"></script>
        <style type="text/css">
        </style>
    </head>
    <body>
        <jsp:useBean id="listaAsig" scope="session" class="server.CargaDatos"/>
        <header>
            <a href="principal.jsp"><img src="css/imagenes/logoMartin.gif"/></a>
            <div class="container-banderas">
                <form action="estudios.jsp" method="post">
                    <input type="hidden" name="language" value="en"/>
                    <input type="image" tabindex="4" src="css/imagenes/british_flag.jpg" alt="Cambiar idioma a Inglés"/>
                </form>

                <form action="estudios.jsp" method="post">
                    <input type="hidden" name="language" value="es"/>
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

        <section id="bachillerato" class="bach">
            <h1>ESTUDIOS</h1> 

            <c:forEach items="${listaCursos}" var="cursos" varStatus="status">
                <c:choose>
                    <c:when test="${status.index==0}">
                        <h3>PRIMERO DE BACHILLERATO</h3>   
                    </c:when>
                    <c:when test="${status.index==4}">
                        <h3>SEGUNDO DE BACHILLERATO</h3>   
                    </c:when>
                </c:choose>
                <article class="curso">
                    <header>    
                        <!--Condición para añadir el icono, según el curso-->
                        <c:choose>                                            
                            <c:when test="${(status.index==0) || (status.index==4)}">
                                <span class="icon-lab"></span>   
                            </c:when>
                            <c:when test="${(status.index==1) || (status.index==5)}">
                                <span class="icon-study"></span>   
                            </c:when>
                            <c:when test="${(status.index==2) || (status.index==6)}">
                                <span class="icon-factory"></span> 
                            </c:when>
                            <c:when test="${(status.index==3) || (status.index==7)}">
                                <span class="icon-globe"></span>   
                            </c:when>
                        </c:choose>                                         
                        <h2>${cursos.descripcion}</h2>
                    </header>
                    <div>
                        <!--Asignaturas por curso-->
                        <c:forEach items="${listaAsignaturas}" var="asig" >
                            <c:if test="${asig.curso.codigo == cursos.codigo}">                                                    
                                <p><a class="asig" href="departamentos.jsp#cd_${asig.departamento.codigo}">${asig.nombre}</a></p> 
                                </c:if>
                            </c:forEach>  
                    </div>
                    <div id="pdf">
                        <form action="Pdf" method="post">   
                            <!--Paso el codigo del curso como hidden, para filtrar las asignaturas-->
                            <input type="hidden" name="curso" value="${cursos.codigo}"/>
                            <input type="hidden" name="bach" value="${cursos.descripcion}"/> 
                            <input type="image" src="css/imagenes/pdf.png" title="Generacion de PDF"/>  
                        </form>
                    </div>
                </article>
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
