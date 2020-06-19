package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.servicios.CanchaService;
import ar.edu.unlam.tallerweb1.servicios.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cancha;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Controller
public class PartidoController extends HttpServlet {

	private PartidoService partidoService;
	private CanchaService canchaService;

	@Autowired
	public PartidoController(PartidoService partidoService, CanchaService canchaService) {
		this.partidoService = partidoService;
		this.canchaService = canchaService;
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

		List<Partido> partidos = this.partidoService.getAll();
		model.put("partidos", partidos);

		return new ModelAndView("partidos", model);
	}

	@RequestMapping(value = "/eliminar-partido/{id}", method = RequestMethod.POST) // TEST REALIZADO Y VERIFICADO
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

		this.partidoService.eliminarPartido(id);

		List<Partido> partidos = this.partidoService.getAll();
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

		List<Cancha> canchas = this.canchaService.getAll();
		model.put("canchas", canchas);

		return new ModelAndView("form-partido", model);

	}

	/*
	 * @RequestMapping(value = "/crear-partido", method = RequestMethod.GET) // TEST
	 * REALIZADO Y VERIFICADO public ModelAndView crearPartido() {
	 * 
	 * ModelMap model = new ModelMap();
	 * 
	 * Partido partido = new Partido();
	 * 
	 * model.put("partido", partido);
	 * 
	 * List<Cancha> canchas = this.canchaService.getAll(); model.put("canchas",
	 * canchas);
	 * 
	 * return new ModelAndView("form-partido", model); }
	 */

	@RequestMapping(path = "/insertar-partido", method = RequestMethod.POST) // TEST REALIZADO Y VERIFICADO
	public ModelAndView insertarPartido(@ModelAttribute("partido") Partido partido, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}

		String mensaje1 = "El partido ";
		String mensaje2 = " ha sido creado con éxito.";

		model.put("msj1", mensaje1);
		model.put("msj2", mensaje2);
		//model.put("id", partido.getId());

		this.partidoService.insertarPartido(partido);
		
		List<Partido> partidos = this.partidoService.getAll();
		model.put("partidos", partidos);
		
		return new ModelAndView("partidos", model);

	}

}
