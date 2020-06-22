package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@Autowired
	public ControladorUsuario(ServicioUsuario servicioUsuario, ServicioBarrio servicioBarrio, ServicioCuenta servicioCuenta) {

		this.servicioUsuario = servicioUsuario;
		this.servicioBarrio = servicioBarrio;
		this.servicioCuenta = servicioCuenta;
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

	@RequestMapping(value = "/invitar-usuario", method = RequestMethod.GET) // TEST REALIZADO Y VERIFICADO
	public ModelAndView invitar(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}

		List<Barrio> barrios = this.servicioBarrio.getAll();
		model.put("barrios", barrios);
		model.put("usuario", new Usuario());

		return new ModelAndView("form-buscar-usuario", model);
	}

	@RequestMapping(value = "/buscar-usuario", method = RequestMethod.POST)
	public ModelAndView buscar(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelMap model = new ModelMap();

		if (session != null) {
			Cuenta cuenta = (Cuenta) session.getAttribute("usuario");
			model.put("cuenta", cuenta);
		}

		List<Usuario> usuarios = this.servicioUsuario.buscar(usuario);
		model.put("usuarios", usuarios);

		return new ModelAndView("lista-usuarios-invitar", model);
	}

}
