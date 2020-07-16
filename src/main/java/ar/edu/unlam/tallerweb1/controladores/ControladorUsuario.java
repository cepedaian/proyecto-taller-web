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
import ar.edu.unlam.tallerweb1.servicios.ServicioEnviarMail;
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
	private ServicioEnviarMail servicioMail;

	@Autowired
	public ControladorUsuario(ServicioUsuario servicioUsuario, ServicioBarrio servicioBarrio, ServicioCuenta servicioCuenta,
			ServicioPartido servicioPartido,ServicioEnviarMail servicioMail) {

		this.servicioUsuario = servicioUsuario;
		this.servicioBarrio = servicioBarrio;
		this.servicioCuenta = servicioCuenta;
		this.servicioPartido = servicioPartido;
		this.servicioMail = servicioMail;
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
		this.servicioMail.enviarMail(emailDestinatario, asunto, cuerpo);

		List<Barrio> barrios = this.servicioBarrio.getAll();
		model.put("barrios", barrios);
		model.put("usuario", new Usuario());

		model.put("mensaje", "La invitacion fue enviada!!!");

		return new ModelAndView("form-buscar-usuario", model);
	}

	
}
