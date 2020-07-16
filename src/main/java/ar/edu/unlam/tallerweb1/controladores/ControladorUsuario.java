package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.modelos.Partido;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelos.Barrio;
import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBarrio;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorUsuario extends HttpServlet {

	private ServicioUsuario servicioUsuario;
	private ServicioBarrio servicioBarrio;
	private ServicioCuenta servicioCuenta;
	private ServicioPartido servicioPartido;

	@Autowired
	public ControladorUsuario(ServicioUsuario servicioUsuario, ServicioBarrio servicioBarrio, ServicioCuenta servicioCuenta, ServicioPartido servicioPartido) {

		this.servicioUsuario = servicioUsuario;
		this.servicioBarrio = servicioBarrio;
		this.servicioCuenta = servicioCuenta;
		this.servicioPartido = servicioPartido;
	}

	@RequestMapping(path = "/show-form-usuario", method = RequestMethod.GET)
	public ModelAndView showForm() {
		ModelMap model = new ModelMap();

		List<Barrio> barrios = this.servicioBarrio.getAll();
		model.put("barrios", barrios);
		model.put("usuario", new Usuario());
		model.put("cuenta", new Cuenta());

		return new ModelAndView("form-usuario", model);
	}
	
	@RequestMapping(value = "/buscar-usuario/{id}", method = RequestMethod.POST)
	public ModelAndView buscar(@ModelAttribute("usuario") Usuario usuario, @PathVariable("id") Long id, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}

		List<Usuario> usuarios = this.servicioUsuario.buscar(usuario);
		model.put("usuarios", usuarios);
		model.put("id-partido", id);

		return new ModelAndView("lista-usuarios-invitar", model);
	}

	@RequestMapping(value = "/invitar-usuario/{id-partido}/{id-usuario}", method = RequestMethod.GET)
	public ModelAndView invitarUsuario(@PathVariable("id-partido") Long idPartido, @PathVariable("id-usuario") Long idUsuario, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}

		Partido partido = this.servicioPartido.getById(idPartido);
		model.put("partido",partido);

		//envio de mail
		Cuenta destinatario = this.servicioCuenta.getByIdUser(idUsuario);
		String emailDestinatario = destinatario.getEmail();
		String asunto = "Te invitaron a un partido";
		String cuerpo = "Hola queriamos avisarte que te han invitado a un partido! <br>";
		cuerpo += "<a href='http://localhost:8080/detalle-partido/" + idPartido + "'>Ver detalle del partido</a>";
		enviarConGMail(emailDestinatario, asunto, cuerpo);

		List<Barrio> barrios = this.servicioBarrio.getAll();
		model.put("barrios", barrios);
		model.put("usuario", new Usuario());

		return new ModelAndView("form-buscar-usuario", model);
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
			//message.setText(cuerpo);
			message.setContent(cuerpo, "text/html; charset=utf-8");
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, "unlam2020");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace(); // Si se produce un error
		}
	}
	
}
