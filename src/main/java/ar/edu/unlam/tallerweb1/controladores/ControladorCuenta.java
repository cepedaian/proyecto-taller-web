package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelos.Barrio;
import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBarrio;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorCuenta {
    private ServicioCuenta servicioCuenta;
    private ServicioBarrio servicioBarrio;

	@Autowired
	public ControladorCuenta(ServicioBarrio servicioBarrio, ServicioCuenta servicioCuenta) {
		this.servicioBarrio = servicioBarrio;
		this.servicioCuenta = servicioCuenta;
	}
	
	private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente también.
		String remitente = "lineadecuatro2020@gmail.com"; // Para la dirección nomcuenta@gmail.com

		java.util.Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "unlam2020"); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatario); // Se podrían añadir varios de la misma
																			// manera
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, "unlam2020");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace(); // Si se produce un error
		}
	}

    @RequestMapping(path = "/insertar-usuario", method = RequestMethod.POST) // TEST REALIZADO Y VERIFICADO
	public ModelAndView insertar(@ModelAttribute("cuenta") Cuenta cuenta, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		if(cuenta.getPassword() == "") {
			String mensaje = "Debe completar contrase?a";
			model.put("error", mensaje);
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
		if(cuenta.getUsuario().getFecha_nac() == null) {
			String mensaje = "Debe completar fecha nacimiento";
			model.put("error", mensaje);
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
		if(cuenta.getUsuario().getBarrio() == null) {
			String mensaje = "Debe completar barrio";
			model.put("error", mensaje);
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
		if(cuenta.getUsuario().getPosicion() == "") {
			String mensaje = "Debe completar la posiciÃ³n";
			model.put("error", mensaje);
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
		if(cuenta.getUsuario().getSexo() == "") {
			String mensaje = "Debe completar sexo";
			model.put("error", mensaje);
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
		
		try {
			this.servicioCuenta.crearCuenta(cuenta);
			String mensaje = "Se registro con exito! Recibira un mail de confirmacion de la cuenta.";
			String emailDestinatario = cuenta.getEmail();
			String asunto = "Confirmacion de cuenta";
			String cuerpo = "Te damos la Bienvenida a LineaDeCuatro! "
					+ "Si recibiste este Email, es para confirmar que tu cuenta se creo con exito! "
					+ "Ya podes usar nuestra aplicacion y participar de partidos de futbol con amigos y otras personas.";

			enviarConGMail(emailDestinatario, asunto, cuerpo);
			
			model.put("msj", mensaje);
			return new ModelAndView("home", model);

		} catch (Exception e) {
			model.put("error", e.getMessage());
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
	}
}
