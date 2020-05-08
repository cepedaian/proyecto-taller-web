package ar.edu.unlam.tallerweb1.controladores;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.SaludarService;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;



@Controller
public class MiControlador {
	
	private SaludarService saludarService;
	
	@Autowired
	public MiControlador(SaludarService saludarService){
		this.saludarService = saludarService;
	}
	
	
	@RequestMapping(value="/saludar", method= RequestMethod.GET)
	public ModelAndView saludarRequestParam(@RequestParam("nombre") String nombre){
		
		ModelMap model = new ModelMap();
		
		model.put("nombre",saludarService.gritar(nombre));
		
		return new ModelAndView ("saludo", model);
	}
	
	@RequestMapping(value="/saludar/{nombre}", method= RequestMethod.POST)
	
	public ModelAndView saludar(@PathVariable("nombre") String nombre){
			
		ModelMap model = new ModelMap();
		
		model.put("nombre",nombre.toUpperCase());
		
		return new ModelAndView ("saludo", model);
	}

/*
	@RequestMapping(path = "/saludarA", method = RequestMethod.POST)
	public ModelAndView SaludarNombreApellido(@ModelAttribute("persona") Persona persona, HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
	
		model.put("nombre", persona.getNombre());
		model.put("apellido", persona.getApellido());
		
		
		return new ModelAndView("saludo", model);
	}*/
	
}
