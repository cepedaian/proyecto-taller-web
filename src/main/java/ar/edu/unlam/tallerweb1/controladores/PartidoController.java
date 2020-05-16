package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import ar.edu.unlam.tallerweb1.servicios.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Partido;

@Controller
public class PartidoController {
	
	private PartidoService partidoService;

	@Autowired
	public PartidoController(PartidoService partidoService){
		this.partidoService = partidoService;
	}
	
	@RequestMapping("/home")
	public ModelAndView irAHome() {

		ModelMap modelo = new ModelMap();
		
		Partido partido = new Partido();
		modelo.put("partido", partido);
		
		return new ModelAndView("home", modelo);
	}
	
	
	@RequestMapping(value="/mostrar-partidos", method= RequestMethod.POST)
	public ModelAndView mostrarPartidos(){
		
		ModelMap model = new ModelMap();

		List<Partido> partidos = this.partidoService.getAll();
		model.put("partidos", partidos);

		return new ModelAndView ("partidos", model);
	}


}
