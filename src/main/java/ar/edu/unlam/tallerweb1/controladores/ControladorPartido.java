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
import ar.edu.unlam.tallerweb1.servicios.ServicioEnviarMail;
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
	private ServicioEnviarMail servicioMail;
	
	@Autowired
	public ControladorPartido(ServicioPartido servicioPartido, ServicioCancha servicioCancha,
			ServicioNotificacion servicioNotificacion, ServicioUsuario servicioUsuario, ServicioCuenta servicioCuenta,
			ServicioBarrio servicioBarrio,ServicioEnviarMail servicioMail) {
		this.servicioPartido = servicioPartido;
		this.servicioCancha = servicioCancha;
		this.servicioNotificacion = servicioNotificacion;
		this.servicioUsuario = servicioUsuario;
		this.servicioCuenta = servicioCuenta;
		this.servicioBarrio = servicioBarrio;
		this.servicioMail = servicioMail;
	}

	@RequestMapping("/home")
	public ModelAndView irAHome() {

		ModelMap modelo = new ModelMap();

		Partido partido = new Partido();
		modelo.put("partido", partido);

		return new ModelAndView("home", modelo);
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
		
		this.servicioPartido.eliminarPartido(id);

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

		Partido partido = this.servicioPartido.getByIdLazyMode(id);
		model.put("partido", partido);

		Set<Usuario> usuarios = this.servicioPartido.getByIdLazyMode(id).getJugadores();

		model.put("usuarios", usuarios);

		//mostrar boton de unirse
		if(cuenta != null &&
				(!cuenta.getUsuario().getUserName().equals(partido.getOrganizador())) &&
				partido.getCantidadJugadores() > usuarios.size() &&
				!estaUnido(cuenta.getUsuario(), usuarios) && cuenta.getUsuario().getSexo().equals(partido.getSexo())) {
			model.put("btnUnirse", true);
		}
		
		if(cuenta != null &&
				(estaUnido(cuenta.getUsuario(), usuarios)) && 
				!cuenta.getUsuario().getUserName().equals(partido.getOrganizador()) )  {
			model.put("btnBajarse", true);
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
		String asunto = "Nuevo jugador!";
		String cuerpo = remitente + " se unió a tu partido!";

		this.servicioMail.enviarMail(emailDestinatario, asunto, cuerpo);

		//notificacion app
		
		notificacion.setAsunto(asunto);
		notificacion.setCuerpo(cuerpo);
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
	@RequestMapping(value = "/baja-partido/{id}", method = RequestMethod.GET)
	public ModelAndView bajarse(@PathVariable("id") Long id, HttpServletRequest request) {

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
		
		Usuario usuario = cuenta.getUsuario();
		//envio de mail
		String remitente = usuario.getUserName();
		String emailDestinatario = this.servicioCuenta.getEmailByIdUsuario(destinatario.getId());
		String asunto = "Uno Menos!";
		String cuerpo = remitente + " se bajó de tu partido!";

		this.servicioMail.enviarMail(emailDestinatario, asunto, cuerpo);

		//notificacion app
		notificacion.setAsunto(asunto);
		notificacion.setCuerpo(cuerpo);
		notificacion.setDestinatario(destinatario);
		notificacion.setPartido(partido);
		notificacion.setRemitente(usuario.getUserName());

		this.servicioPartido.bajarse(partido, usuario);
		this.servicioNotificacion.crear(notificacion);

		String msj = "Te bajaste del partido satisfactoriamente.";
		model.put("msj", msj);
		List<Partido> partidos = this.servicioPartido.getAll();
		model.put("partidos", partidos);
		return new ModelAndView("partidos", model);

	}

	
	@RequestMapping(value = "/show-invitar-usuario-partido/{id}", method = RequestMethod.GET) // TEST REALIZADO Y VERIFICADO
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

	@RequestMapping(value = "/eliminar-participante/{id_usuario}/{id_partido}", method = RequestMethod.GET) // TEST REALIZADO Y VERIFICADO
	public ModelAndView eliminarParticipante(@PathVariable("id_usuario") Long id_usuario,
			@PathVariable("id_partido") Long id_partido, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}

		String mensaje = "El participante ha sido eliminado. ";

		model.put("msj", mensaje);
		
		this.servicioPartido.eliminarParticipante(id_usuario, id_partido);

		List<Partido> partidos = this.servicioPartido.getAll();
		model.put("partidos", partidos);

		//envio de mail
		Cuenta destinatario = this.servicioCuenta.getByIdUser(id_usuario);
		String emailDestinatario = destinatario.getEmail();
		String asunto = "Te bajaron del partido";
		String cuerpo = "Lamentablemente el organizador te bajo del partido!";
		this.servicioMail.enviarMail(emailDestinatario, asunto, cuerpo);

		return new ModelAndView("partidos", model);
	}


}
