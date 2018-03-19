package server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.*;
import DTO.*;
import com.oreilly.servlet.MultipartRequest;
import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RafaMar
 */
public class AltaNews extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession miSesion = request.getSession();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IES_MARTINPU");
        NoticiasJpaController ctrlNoticias = new NoticiasJpaController(emf);
        DepartamentosJpaController ctrlDepartamentos = new DepartamentosJpaController(emf);

        if (request.getContentType() != null) {
            /*Definimos la ruta de subida*/
            String rutaSubida = getServletContext().getRealPath("/images/news");
            int nuevoTamaño = 39998995;//4MB
            MultipartRequest mr = new MultipartRequest(request, rutaSubida,nuevoTamaño);
            //MultipartRequest mrAbsoluta = new MultipartRequest(request, "C:/pruebaImag");

            /*Creamos nuestra noticia*/
            Noticias newNoticia = new Noticias();
            newNoticia.setTitular(mr.getParameter("titular"));
            newNoticia.setDescripcion(mr.getParameter("descripcion"));
            newNoticia.setSubtitulo(mr.getParameter("subtitulo"));

            /*Obtenemos el nombre de la imagen*/
            Enumeration nfiles = mr.getFileNames();
            String nombreImg = mr.getFile((String) nfiles.nextElement()).getName();
            newNoticia.setImagen(nombreImg);

            /*Seteamos el codigo de departamento de la noticia*/
            int codigoDpto = Integer.parseInt(mr.getParameter("departamento"));
            Departamentos departamento = ctrlDepartamentos.findDepartamentos(codigoDpto);
            newNoticia.setCodigoDpto(departamento);

            /*Actualizamos la fecha de la noticia*/
            Date fechaAct = Calendar.getInstance().getTime();
            newNoticia.setFechaPublicacion(fechaAct);

            ctrlNoticias.create(newNoticia);
            miSesion.setAttribute("altaNoticia", "Se ha registrado una nueva Noticia");
        }

        response.sendRedirect("administrador.jsp");

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
