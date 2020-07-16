package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelos.Barrio;
import ar.edu.unlam.tallerweb1.modelos.Partido;
import ar.edu.unlam.tallerweb1.servicios.ServicioBarrio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelos.Cancha;
import ar.edu.unlam.tallerweb1.servicios.ServicioCancha;

@Controller
public class ControladorCancha {
	
	private ServicioCancha servicioCancha;
	private ServicioBarrio servicioBarrio;
	
	@Autowired
	public ControladorCancha(ServicioCancha servicioCancha, ServicioBarrio servicioBarrio) {
		this.servicioCancha = servicioCancha;
		this.servicioBarrio = servicioBarrio;
	}
	
	@RequestMapping(path = "/mostrar-canchas", method = RequestMethod.GET)//TEST REALIZADO Y VERIFICADO
	public ModelAndView mostrarCanchas() {
		ModelMap model = new ModelMap();
		
		List<Cancha> canchas = this.servicioCancha.getAll();
		model.put("canchas", canchas);
		
		Barrio barrio = new Barrio();
		
		return new ModelAndView("canchas", model);
	}

	@RequestMapping(path = "/show-form-cancha", method = RequestMethod.GET)//TEST REALIZADO Y VERIFICADO
	public ModelAndView showFormCancha() {
		ModelMap model = new ModelMap();

		List<Barrio> barrios = this.servicioBarrio.getAll();
		model.put("barrios", barrios);
		model.put("cancha", new Cancha());
		
		
		
		return new ModelAndView("form-cancha", model);
	}
	
	@RequestMapping(path = "/crear-cancha", method = RequestMethod.POST)//TEST REALIZADO Y VERIFICADO - PREGUNTAR TEMA "ID"
	public ModelAndView crearCancha(@ModelAttribute("cancha") Cancha cancha, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		Long id = this.servicioCancha.crearCancha(cancha);

		if(id!=0) {
			model.put("mensaje", "La cancha se creo con exito!!!");
		}

		Partido partido = new Partido();
		model.put("partido", partido);

		List<Cancha> canchas = this.servicioCancha.getAll();
		model.put("canchas", canchas);

		return new ModelAndView("form-partido", model);
	}

	@RequestMapping(value="/eliminar-cancha/{id}", method= RequestMethod.POST) //TEST REALIZADO Y VERIFICADO
	public ModelAndView eliminarCancha(@PathVariable("id") Long id){

		ModelMap model = new ModelMap();

		this.servicioCancha.eliminarCancha(id);

		return new ModelAndView ("cancha-eliminada", model);
	}
}
