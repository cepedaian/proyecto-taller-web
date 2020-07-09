package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.dtos.PartidoDTO;
import ar.edu.unlam.tallerweb1.modelos.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioBarrio;
import ar.edu.unlam.tallerweb1.servicios.ServicioCancha;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorPartido extends HttpServlet {

	private ServicioPartido servicioPartido;
	private ServicioCancha servicioCancha;
	private ServicioNotificacion servicioNotificacion;
	private ServicioUsuario servicioUsuario;
	private ServicioCuenta servicioCuenta;
	private ServicioBarrio servicioBarrio;

	@Autowired
	public ControladorPartido(ServicioPartido servicioPartido, ServicioCancha servicioCancha,
			ServicioNotificacion servicioNotificacion, ServicioUsuario servicioUsuario, ServicioCuenta servicioCuenta,ServicioBarrio servicioBarrio) {
		this.servicioPartido = servicioPartido;
		this.servicioCancha = servicioCancha;
		this.servicioNotificacion = servicioNotificacion;
		this.servicioUsuario = servicioUsuario;
		this.servicioCuenta = servicioCuenta;
		this.servicioBarrio = servicioBarrio;
	}

	@RequestMapping("/home")
	public ModelAndView irAHome() {

		ModelMap modelo = new ModelMap();

		Partido partido = new Partido();
		modelo.put("partido", partido);

		return new ModelAndView("home", modelo);
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

	@RequestMapping(value = "/mostrar-partidos", method = RequestMethod.GET) // TEST REALIZADO Y VERIFICADO
	public ModelAndView mostrarPartidos(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}

		List<Partido> partidos = this.servicioPartido.getAll();
		model.put("partidos", partidos);

		return new ModelAndView("partidos", model);
	}

	@RequestMapping(value = "/eliminar-partido/{id}", method = RequestMethod.GET) // TEST REALIZADO Y VERIFICADO
	public ModelAndView eliminarPartido(@PathVariable("id") Long id, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}

		String mensaje = "El partido ha sido eliminado. ";

		model.put("msj", mensaje);
		
		Partido partido = this.servicioPartido.getByIdLazyMode(id);
		
		this.servicioPartido.eliminarPartido(partido);

		List<Partido> partidos = this.servicioPartido.getAll();
		model.put("partidos", partidos);

		return new ModelAndView("partidos", model);
	}

	@RequestMapping(value = "/crear-partido", method = RequestMethod.GET) // TEST REALIZADO Y VERIFICADO
	public ModelAndView crearPartido(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}

		Partido partido = new Partido();
		model.put("partido", partido);

		List<Cancha> canchas = this.servicioCancha.getAll();
		model.put("canchas", canchas);

		return new ModelAndView("form-partido", model);

	}

	@RequestMapping(path = "/insertar-partido", method = RequestMethod.POST) // TEST REALIZADO Y VERIFICADO
	public ModelAndView insertarPartido(@ModelAttribute("partido") Partido partido, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();
		Cuenta cuenta = new Cuenta();
		if (session != null) {
			cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		} else {
			model.put("msj", "Para crear partido te tenes que loguear. No hagas trampa!");
		}

		model.put("msj", "El partido se creo con exito.");

		this.servicioPartido.insertarPartido(partido);
		this.servicioPartido.unirse(partido, cuenta.getUsuario());

		List<Partido> partidos = this.servicioPartido.getAll();
		model.put("partidos", partidos);

		return new ModelAndView("partidos", model);

	}

	@RequestMapping(value = "/detalle-partido/{id}", method = RequestMethod.GET)
	public ModelAndView verDetalle(@PathVariable("id") Long id, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		Cuenta cuenta = new Cuenta();
		if (session != null) {
			cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}

		Partido partido = this.servicioPartido.getById(id);
		model.put("partido", partido);

		Set<Usuario> usuarios = this.servicioPartido.getByIdLazyMode(id).getJugadores();

		model.put("usuarios", usuarios);

		//mostrar boton de unirse
		if(cuenta != null &&
				(!cuenta.getUsuario().getUserName().equals(partido.getOrganizador())) &&
				partido.getCantidadJugadores() > usuarios.size() &&
				!estaUnido(cuenta.getUsuario(), usuarios)) {
			model.put("btnUnirse", true);
		}

		return new ModelAndView("detalle-partido", model);

	}

	private boolean estaUnido(Usuario usuario, Set<Usuario> usuarios) {
		boolean unido = false;
		for (Usuario user: usuarios) {
			if(user.getUserName().equals(usuario.getUserName())) {
				unido = true;
				break;
			}
		}
		return unido;
	}

	@RequestMapping(value = "/unirse-partido/{id}", method = RequestMethod.GET)
	public ModelAndView unirse(@PathVariable("id") Long id, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		Cuenta cuenta = new Cuenta();
		Notificacion notificacion = new Notificacion();
		if (session != null) {
			cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}
		Partido partido = this.servicioPartido.getById(id);
		Usuario destinatario = this.servicioUsuario.getByUserName(partido.getOrganizador());
		
		//envio de mail
		Usuario usuario = cuenta.getUsuario();
		String remitente = usuario.getUserName();
		String emailDestinatario = this.servicioCuenta.getEmailByIdUsuario(destinatario.getId());
		String asunto = remitente + " se unió a tu partido!";
		String cuerpo = asunto;

		enviarConGMail(emailDestinatario, asunto, cuerpo);

		//notificacion app

		notificacion.setDestinatario(destinatario);
		notificacion.setPartido(partido);
		notificacion.setRemitente(usuario.getUserName());

		this.servicioPartido.unirse(partido, usuario);
		this.servicioNotificacion.crear(notificacion);

		String msj = "Te uniste al partido satisfactoriamente.";
		model.put("msj", msj);
		List<Partido> partidos = this.servicioPartido.getAll();
		model.put("partidos", partidos);
		return new ModelAndView("partidos", model);

	}
	
	@RequestMapping(value = "/invitar-usuario-partido/{id}", method = RequestMethod.GET) // TEST REALIZADO Y VERIFICADO
	public ModelAndView invitar(@PathVariable("id") Long id,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}
		
		Partido partido = this.servicioPartido.getById(id);
		
		model.put("partido",partido);
		
		List<Barrio> barrios = this.servicioBarrio.getAll();
		model.put("barrios", barrios);
		model.put("usuario", new Usuario());

		return new ModelAndView("form-buscar-usuario", model);
	}
}
