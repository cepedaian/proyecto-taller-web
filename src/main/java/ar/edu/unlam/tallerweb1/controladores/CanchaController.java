package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.servicios.BarrioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cancha;
import ar.edu.unlam.tallerweb1.servicios.CanchaService;

@Controller
public class CanchaController {
	
	private CanchaService canchaService;
	private BarrioService barrioService;
	
	@Autowired
	public CanchaController(CanchaService canchaService, BarrioService barrioService) {
		this.canchaService = canchaService;
		this.barrioService = barrioService;
	}
	
	@RequestMapping(path = "/mostrar-canchas", method = RequestMethod.GET)
	public ModelAndView mostrarCanchas() {
		ModelMap model = new ModelMap();
		
		List<Cancha> canchas = this.canchaService.getAll();
		model.put("canchas", canchas);
		
		return new ModelAndView("canchas", model);
	}

	@RequestMapping(path = "/show-form-cancha", method = RequestMethod.GET)
	public ModelAndView showFormCancha() {
		ModelMap model = new ModelMap();

		List<Barrio> barrios = this.barrioService.getAll();
		model.put("barrios", barrios);
		model.put("cancha", new Cancha());

		return new ModelAndView("form-cancha", model);
	}
	
	@RequestMapping(path = "/crear-cancha", method = RequestMethod.POST)
	public ModelAndView crearCancha(@ModelAttribute("cancha") Cancha cancha, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		Long id = this.canchaService.crearCancha(cancha);

		if(id!=0) {
			model.put("mensaje", "La cancha se creo con exito!!!");
		}

		List<Cancha> canchas = this.canchaService.getAll();
		model.put("canchas", canchas);
		
		return new ModelAndView ("canchas", model);
	}

	@RequestMapping(value="/eliminar-cancha/{id}", method= RequestMethod.POST)
	public ModelAndView eliminarCancha(@PathVariable("id") Long id){

		ModelMap model = new ModelMap();

		this.canchaService.eliminarCancha(id);

		return new ModelAndView ("cancha-eliminada", model);
	}
}
