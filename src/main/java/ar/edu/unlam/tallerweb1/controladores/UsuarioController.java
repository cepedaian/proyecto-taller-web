package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.filter.UsuarioFilter;
import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.BarrioService;
import ar.edu.unlam.tallerweb1.servicios.CuentaService;
import ar.edu.unlam.tallerweb1.servicios.UsuarioService;

@Controller
public class UsuarioController {

	private UsuarioService usuarioService;
	private BarrioService barrioService;
	private CuentaService cuentaService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService, BarrioService barrioService, CuentaService cuentaService) {

		this.usuarioService = usuarioService;
		this.barrioService = barrioService;
		this.cuentaService = cuentaService;
	}

	@RequestMapping(path = "/show-form-usuario", method = RequestMethod.GET)
	public ModelAndView CrearUsuario() {
		ModelMap model = new ModelMap();

		List<Barrio> barrios = this.barrioService.getAll();
		model.put("barrios", barrios);
		model.put("usuario", new Usuario());
		model.put("cuenta", new Cuenta());

		return new ModelAndView("form-usuario", model);
	}

	@RequestMapping(path = "/insertar-usuario", method = RequestMethod.POST) //TEST REALIZADO Y VERIFICADO
	public ModelAndView InsertarUsuario(@ModelAttribute("cuenta") Cuenta cuenta, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		try {
			this.cuentaService.crearCuenta(cuenta);
			String mensaje = "Se registro con exito!!!";
			model.put("msj", mensaje);
			return new ModelAndView("home", model);
		} catch (Exception e) {
			model.put("error", e.getMessage());
			List<Barrio> barrios = this.barrioService.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
	}

	@RequestMapping(value = "/invitar-usuario", method = RequestMethod.GET) //TEST REALIZADO Y VERIFICADO
	public ModelAndView InvitarUsuario() {
		ModelMap model = new ModelMap();

		// UsuarioFilter usuarioFilter = new UsuarioFilter();
		// model.put("usuarioFilter",usuarioFilter);

		List<Barrio> barrios = this.barrioService.getAll();
		model.put("barrios", barrios);
		model.put("usuario", new Usuario());

		return new ModelAndView("form-invitado", model);
	}

	@RequestMapping(value = "/buscar-usuario", method = RequestMethod.POST)
	public ModelAndView BuscarUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		List<Usuario> usuarios = this.usuarioService.buscarUsuario(usuario);
		model.put("usuarios", usuarios);

		return new ModelAndView("mostrar-usuarios", model);
	}

}
