package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.modelos.Notificacion;
import ar.edu.unlam.tallerweb1.modelos.Partido;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControladorLogin extends HttpServlet {

	private ServicioLogin servicioLogin;
	private ServicioPartido servicioPartido;
	private ServicioNotificacion servicioNotificacion;

	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin, ServicioPartido servicioPartido, ServicioNotificacion servicioNotificacion) {
		this.servicioLogin = servicioLogin;
		this.servicioPartido = servicioPartido;
		this.servicioNotificacion = servicioNotificacion;
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelMap modelo = new ModelMap();
		Cuenta cuenta = new Cuenta();
		modelo.put("cuenta", cuenta);

		return new ModelAndView("login", modelo);
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		ModelMap model = new ModelMap();
		model.put("msj", "Hasta el proximo partido!");

		return new ModelAndView("home", model);
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("cuenta") Cuenta cuenta, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		String view = "login";

		Cuenta usuarioBuscado = servicioLogin.getCuenta(cuenta);
		if (usuarioBuscado != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("usuario",usuarioBuscado);
			view = "partidos";
			model.put("cuenta", usuarioBuscado);
			List<Partido> partidos = this.servicioPartido.getAll();
			model.put("partidos", partidos);

			List<Notificacion> notificaciones = this.servicioNotificacion.getNotificacionesByUsuarioId(usuarioBuscado.getUsuario().getId());
			model.put("notificaciones",notificaciones);
		} else {
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView(view, model);
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("home");
	}

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la
	// url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/home");
	}
}
