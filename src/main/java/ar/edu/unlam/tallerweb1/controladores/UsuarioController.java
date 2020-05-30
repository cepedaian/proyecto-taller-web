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

import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.BarrioService;
import ar.edu.unlam.tallerweb1.servicios.UsuarioService;

@Controller
public class UsuarioController {

	private UsuarioService usuarioService;
	private BarrioService barrioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService, BarrioService barrioService) {
		
		this.usuarioService = usuarioService;
		this.barrioService = barrioService;
		
	}
	
	@RequestMapping (path="/crear-usuario", method = RequestMethod.GET)
	public ModelAndView CrearUsuario() {
		
		ModelMap model = new ModelMap();
		
		List<Barrio> barrios = this.barrioService.getAll();
		model.put("barrios", barrios);
		model.put("usuario", new Usuario());
		
		return new ModelAndView("form-usuario", model);
		
	}
	
	@RequestMapping(path="/insertar-usuario", method = RequestMethod.POST)
	public ModelAndView InsertarUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		
		ModelMap model = new ModelMap();

		this.usuarioService.crearUsuario(usuario);
		
		return new ModelAndView ("confirmar-usuario", model);
		
	}
}
