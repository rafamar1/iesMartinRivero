

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="DAO.*"%>
<%@page import="DTO.*"%>

<!DOCTYPE html>
<jsp:include page="/CargaDatos"/>   
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
                <a title="Abrie Menú Desplegable" href="#" class="boton_menu" id="bars"><span class="icon-menu"></span></a>
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

        <h1>ESTUDIOS</h1> 

        <section class="Bach Primero">
            <header>
                <h3>PRIMERO DE BACHILLERATO</h3>                
            </header>            
            <article class="ciencias">
                <header>
                    <h3>MODALIDAD DE CIENCIAS</h3>
                </header> 
                <c:forEach items="${listaCursos}" var="cursos" varStatus="status">
                    <c:if test="${status.index<2}">   
                        <section class="allDepart">                                        
                            <header>
                                <c:choose>
                                    <c:when test="${status.index==0}">
                                        <span class="icon-lab"></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="icon-study"></span>
                                    </c:otherwise>
                                </c:choose>                                                
                                <h2>${cursos.descripcion}</h2>
                            </header>
                            <div>   
                                <c:forEach items="${listaAsignaturas}" var="asig" >
                                    <c:if test="${asig.curso.codigo == cursos.codigo}">                                                    
                                        <a href="departamentos.jsp#cd_${asig.departamento.codigo}"><p><span class="asig">${asig.nombre}</span></p></a>
                                                </c:if>
                                            </c:forEach>  
                            </div> 
                            <div id="prueba">
                                <form action="Pdf" method="post">   
                                    <i class="icon-file-pdf"></i> <input type="submit" value="PFD" alt="Generacion de PDF"/>                                                
                                </form>
                            </div>
                        </section>  
                    </c:if>
                </c:forEach> 
            </article>
            <article class="sociales">
                <header>
                    <h3>MODALIDAD DE SOCIALES</h3>
                </header>
                <c:forEach items="${listaCursos}" var="cursos" varStatus="status">
                    <c:if test="${status.index>1 && status.index<4}">                                        
                        <section class="allDepart">                                        
                            <header>
                                <c:choose>
                                    <c:when test="${status.index==2}">
                                        <span class="icon-factory"></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="icon-study"></span>
                                    </c:otherwise>
                                </c:choose> 
                                <h2>${cursos.descripcion}</h2>
                            </header>
                            <div>   
                                <c:forEach items="${listaAsignaturas}" var="asig" >
                                    <c:if test="${asig.curso.codigo == cursos.codigo}">                                                    
                                        <a href="departamentos.jsp#cd_${asig.departamento.codigo}"><p><span class="asig">${asig.nombre}</span></p></a>
                                                </c:if>
                                            </c:forEach>  
                            </div>
                            <form action="#" method="post">
                                <input type="submit" value="PFD" alt="Generacion de PDF"/>  
                            </form>
                        </section>         
                    </c:if>
                </c:forEach> 
            </article>
        </section>
        <section class="Bach Segundo">
            <header>
                <h3>SEGUNDO DE BACHILLERATO</h3>                
            </header>            
            <article class="ciencias">
                <header>
                    <h3>MODALIDAD DE CIENCIAS</h3>
                </header> 
                <c:forEach items="${listaCursos}" var="cursos" varStatus="status">
                    <c:if test="${status.index>3 && status.index<6}"> 
                        <section class="allDepart">                                        
                            <header>
                                <c:choose>
                                    <c:when test="${status.index==4}">
                                        <span class="icon-lab"></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="icon-study"></span>
                                    </c:otherwise>
                                </c:choose> 
                                <h2>${cursos.descripcion}</h2>
                            </header>
                            <div>   
                                <c:forEach items="${listaAsignaturas}" var="asig" >
                                    <c:if test="${asig.curso.codigo == cursos.codigo}">                                                    
                                        <a href="departamentos.jsp#cd_${asig.departamento.codigo}"><p><span class="asig">${asig.nombre}</span></p></a>
                                                </c:if>
                                            </c:forEach>  
                            </div>
                            <form action="#" method="post">
                                <input type="submit" value="PFD" alt="Generacion de PDF"/>  
                            </form>

                        </section>  
                    </c:if>
                </c:forEach> 
            </article>
            <article class="sociales">
                <header>                                    
                    <h3>MODALIDAD DE SOCIALES</h3>
                </header>
                <c:forEach items="${listaCursos}" var="cursos" varStatus="status">
                    <c:if test="${status.index>5}">                                        
                        <section class="allDepart">                                        
                            <header>
                                <c:choose>
                                    <c:when test="${status.index==6}">
                                        <span class="icon-factory"></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="icon-globe"></span>
                                    </c:otherwise>
                                </c:choose> 
                                <h2>${cursos.descripcion}</h2>
                            </header>
                            <div>   
                                <c:forEach items="${listaAsignaturas}" var="asig" >
                                    <c:if test="${asig.curso.codigo == cursos.codigo}">                                                    
                                        <a href="departamentos.jsp#cd_${asig.departamento.codigo}"><p><span class="asig">${asig.nombre}</span></p></a>
                                                </c:if>
                                            </c:forEach>  
                            </div> 
                            <form action="#" method="post">
                                <input type="submit" value="PFD" alt="Generacion de PDF" >  
                            </form>
                        </section>         
                    </c:if>
                </c:forEach> 
            </article>
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
