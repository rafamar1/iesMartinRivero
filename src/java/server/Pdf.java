package server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.AsignaturasJpaController;
import DTO.Asignaturas;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Pdf extends HttpServlet {

    public void proceso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/pdf");
        OutputStream ficheroPdf = response.getOutputStream();

        try {
            try {
                //fuentes
                Font fuenteNegrita = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, BaseColor.BLACK);
                Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, BaseColor.BLACK);

                // Se crea el documento
                Document documento = new Document();
                PdfWriter write = PdfWriter.getInstance(documento, ficheroPdf);
                write.setInitialLeading(20);
                documento.open();
                //-------------------

                //titulo del pdf
                Paragraph parrafo = new Paragraph();
                Font titulo = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.GRAY);
                parrafo.add(new Phrase("I.E.S Martín Rivero", titulo));
                parrafo.setAlignment(Element.ALIGN_CENTER);
                documento.add(parrafo);
                
                //saltos de linea
                documento.add(new Phrase("\n"));
                documento.add(new Phrase("\n"));
                
                //tabla de Datos (sin borde)
                PdfPTable tabla = new PdfPTable(2);
                tabla.setTotalWidth(new float[]{60, 200});//anchura de las celdas
                tabla.getDefaultCell().setBorder(Rectangle.NO_BORDER);//celdas sin bordes
                tabla.setHorizontalAlignment(Element.ALIGN_LEFT);//alineamos la tabla a la izquierda
                tabla.setWidthPercentage(70);//porcentaje que ocupa la tabla

                //titulo de la tabla
                Font titulo2 = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE);
                Paragraph parrafoDatosCliente = new Paragraph();
                Phrase tituloDatosCliente = new Phrase("Datos del Centro", titulo2);
                parrafoDatosCliente.add(tituloDatosCliente);
                parrafoDatosCliente.setAlignment(Element.ALIGN_CENTER);

                PdfPCell celdaDatosCliente = new PdfPCell(parrafoDatosCliente);
                celdaDatosCliente.setColspan(2);
                celdaDatosCliente.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celdaDatosCliente.setPaddingTop(10);
                celdaDatosCliente.setPaddingBottom(10);
                tabla.addCell(celdaDatosCliente);

                tabla.addCell(new Phrase("Dirección:", fuenteNegrita));

                tabla.addCell(new Phrase("Calle Fernando de los Ríos, 1, 29400 Ronda", fuenteNormal));

                tabla.addCell(new Phrase("Teléfono:", fuenteNegrita));

                tabla.addCell(new Phrase("(+34)952 16 99 07", fuenteNormal));

                tabla.addCell(new Phrase("Fax:", fuenteNegrita));

                tabla.addCell(new Phrase("(+34)952 16 99 13", fuenteNormal));

                tabla.addCell(new Phrase("E-mail:", fuenteNegrita));

                tabla.addCell(new Phrase("info@iesmartinrivero.com", fuenteNormal));

                // Tabla de asignaturas
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("IES_MARTINPU");
                AsignaturasJpaController ctrlAsignaturas = new AsignaturasJpaController(emf);
                List<Asignaturas> listaAsignaturas = ctrlAsignaturas.findAsignaturasEntities();
                
                String bachillerato=request.getParameter("bach");

                PdfPTable tablaPedido = new PdfPTable(1);

                PdfPCell celdaArticulo = new PdfPCell(new Phrase("Asignarutas de "+bachillerato, fuenteNegrita));
                celdaArticulo.setBackgroundColor(BaseColor.LIGHT_GRAY);
                tablaPedido.addCell(celdaArticulo);
                
                //recogemos el parametros pasado como hidden en el form
                String curso=request.getParameter("curso");
                
                for (Asignaturas asig : listaAsignaturas) {
                    if  (asig.getCurso().getCodigo() == Integer.parseInt(curso)){
                        tablaPedido.addCell(new Phrase(asig.getNombre(), fuenteNormal));
                    }
                }
                
                documento.add(tabla);
                
                 //saltos de linea
                documento.add(new Phrase("\n"));
                documento.add(new Phrase("\n"));
                
                documento.add(tablaPedido);
                
                // Cerramos el documento ---------------------
                documento.close();

            } catch (Exception ex) {
                ex.getMessage();
            }

        } finally {
            ficheroPdf.close();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        proceso(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        proceso(req, res);
    }
}
