/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import DAO.*;
import DTO.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RafaMar
 */
public class CargaDatos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IES_MARTINPU");

        NoticiasJpaController ctrlNoticias = new NoticiasJpaController(emf);
        DepartamentosJpaController ctrlDepartamentos = new DepartamentosJpaController(emf);
        AreaDptoJpaController ctrlAreaDpto = new AreaDptoJpaController(emf);
        AsignaturasJpaController ctrlAsignaturas = new AsignaturasJpaController(emf);
        CursosJpaController ctrlCursos = new CursosJpaController(emf);
        int ultimaNoticia = ctrlNoticias.getNoticiasCount();

        List<Noticias> listaNoticias = ctrlNoticias.findNoticiasEntities();
        List<Noticias> listaAux = new ArrayList();
        /*Filtro de Noticias*/
        if (request.getParameter("codigoDepart") != null && !request.getParameter("codigoDepart").equals("")) {
            int codigoDepart = Integer.parseInt(request.getParameter("codigoDepart"));
            for (Noticias noticia : listaNoticias) {
                if (noticia.getCodigoDpto().getCodigo() == codigoDepart) {
                    listaAux.add(noticia);
                }
            }
            request.setAttribute("listaNoticias", listaAux);
        } else {
            request.setAttribute("listaNoticias", listaNoticias);
        }

        List<Noticias> listaNoticiasPrincipal = ctrlNoticias.findNoticiasEntities(4, ultimaNoticia - 4);
        List<AreaDpto> listaAreaDpto = ctrlAreaDpto.findAreaDptoEntities();
        List<Departamentos> listaDepartamentos = ctrlDepartamentos.findDepartamentosEntities();
        List<Asignaturas> listaAsignaturas = ctrlAsignaturas.findAsignaturasEntities();
        List<Cursos> listaCursosBach = ctrlCursos.findCursosEntities(8, 4);

        request.setAttribute("listaNoticiasPrincipal", listaNoticiasPrincipal);
        request.setAttribute("listaAreaDpto", listaAreaDpto);
        request.setAttribute("listaDepartamentos", listaDepartamentos);
        request.setAttribute("listaAsignaturas", listaAsignaturas);
        request.setAttribute("listaCursos", listaCursosBach);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
