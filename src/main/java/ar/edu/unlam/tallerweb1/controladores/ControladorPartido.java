package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.dtos.PartidoDTO;
import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.servicios.ServicioCancha;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelos.Cancha;
import ar.edu.unlam.tallerweb1.modelos.Partido;

@Controller
public class ControladorPartido extends HttpServlet {

	private ServicioPartido servicioPartido;
	private ServicioCancha servicioCancha;

	@Autowired
	public ControladorPartido(ServicioPartido servicioPartido, ServicioCancha servicioCancha) {
		this.servicioPartido = servicioPartido;
		this.servicioCancha = servicioCancha;
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

		String mensaje1 = "El partido ";
		String mensaje2 = " ha sido eliminado.";

		model.put("msj1", mensaje1);
		model.put("msj2", mensaje2);
		model.put("id", id);

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

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		} else {
			model.put("msj", "Para crear partido te tenes que loguear. No hagas trampa!");
		}

		model.put("msj", "El partido se creo con éxito.");

		this.servicioPartido.insertarPartido(partido);
		
		List<Partido> partidos = this.servicioPartido.getAll();
		model.put("partidos", partidos);
		
		return new ModelAndView("partidos", model);

	}

	@RequestMapping(value = "/detalle-partido/{id}", method = RequestMethod.GET)
	public ModelAndView verDetalle(@PathVariable("id") Long id, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}

		Partido partido = this.servicioPartido.getById(id);
		model.put("partido", partido);

		return new ModelAndView("detalle-partido", model);

	}

}
