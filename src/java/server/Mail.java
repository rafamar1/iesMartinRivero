package server;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail extends HttpServlet {

	protected void proceso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Obtenemos los datos del formulario  
                String destinatario = request.getParameter("destinatario");
		String nombre = request.getParameter("nombre");
                String asunto = request.getParameter("asunto");
		String mensaje = request.getParameter("mensaje");
                

		// Informamos el usuario y el password que envian el correo
		String usuario = "pruebamailserverjava@gmail.com";
		String pass = "javajava";
		
		// Suponemos que el envío en incorrecto por si no se captura alguna excepción.
		// En nuestro caso capturamos siempre Exception pero lo dejamos por si queremos capturar excepciones 
		// de manera detallada.
		String resultado = "Error incontrolado en el envío de emails";

		// Realizamos el envío del email utilizando java mail_1.4.jar
		try {
			// Creamos una clase Properties con los datos del servidor de correo
			Properties servidor = new Properties();
			servidor.put("mail.smtp.host", "smtp.gmail.com");
			servidor.put("mail.smtp.port", "587");
			servidor.put("mail.smtp.auth", "true");
			servidor.put("mail.smtp.starttls.enable", "true");

			// Creamos una sesión que nos permita identificarnos en el servidor con el usuario y pass informados previamente
			Authenticator autenticador = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(usuario, pass);
				}
			};

			// Creamos una sesión SMTP con el servidor
			Session session = Session.getInstance(servidor, autenticador);

			// Una vez identificados montamos el email en formato MIME
			Message email = new MimeMessage(session);

			// Paso 1. Quien envía el mail (en nuestro caso el usuario identificado)
			email.setFrom(new InternetAddress(usuario));
			
			// Paso 2. Indicamos los destinatarios (en nuestro ejemplo sólo podemos enviar a 1 persona)
			InternetAddress[] listaDestinatarios = { new InternetAddress(destinatario) };
			email.setRecipients(Message.RecipientType.TO, listaDestinatarios);
			
			// Paso 3. Indicamos el asunto			
			email.setSubject(asunto);
			
			// Paso 4. Informamos la fecha de hoy
			email.setSentDate(new Date());
			
			// Paso 5. Informamos el mensaje escrito por el usuario.
			email.setText("Correo enviado por:"+nombre+ ". " +mensaje);

			// Una vez creado el objeto Message con el email, se realiza en envío. En caso de fallo elevará una excepción
			Transport.send(email);
			
			// Si hemos llegado a este punto significa que no se ha lanzado ninguna excepción y podemos decir que el email se ha enviado correctamente
			resultado = "Email enviado correctamente. Gracias por utilizar nuestro servicio";
		} 
		
		// Capturamos todas las excepciones
		catch (Exception ex) {
			// Pintamos la pila de ejecución
			ex.printStackTrace();
			
			// Modificamos el resultado de la ejecución para informar al usuario del error
			resultado = "There were an error: " + ex.getMessage();
		} 
		
		// Al finalizar la ejecución devolvemos el resultado y llamamos sl JSP que informa del resultado
		finally {
			request.setAttribute("resultado", resultado);
			getServletContext().getRequestDispatcher("/contacto.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
		proceso(req,res);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
		proceso(req,res);
	}
}