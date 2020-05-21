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

import ar.edu.unlam.tallerweb1.modelo.Cancha;
import ar.edu.unlam.tallerweb1.servicios.CanchaService;

@Controller
public class CanchaController {
	
	private CanchaService canchaService;
	
	@Autowired
	public CanchaController(CanchaService canchaService) {
		
		this.canchaService = canchaService;
	}
	
	@RequestMapping(path = "/mostrar-canchas", method = RequestMethod.GET)
	public ModelAndView mostrarCanchas() {
		ModelMap model = new ModelMap();
		
		List<Cancha> canchas = this.canchaService.getAll();
		model.put("canchas", canchas);
		
		return new ModelAndView("canchas", model);
	}
	
	@RequestMapping(path = "/crear-cancha", method = RequestMethod.GET)
	public ModelAndView crearCancha() {
		
		ModelMap model = new ModelMap();
		Cancha cancha = new Cancha();
		model.put("cancha", cancha);
		
		return new ModelAndView("form-cancha", model);
	}
	
	@RequestMapping(path = "/insertar-cancha", method = RequestMethod.POST)
	public ModelAndView insertarCancha(@ModelAttribute("cancha") Cancha cancha, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		this.canchaService.crearCancha(cancha);
		
		return new ModelAndView ("confirmar-cancha", model);
			
	}
}
