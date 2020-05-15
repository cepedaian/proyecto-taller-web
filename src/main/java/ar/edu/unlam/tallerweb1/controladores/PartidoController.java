package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPartidoImpl;

@Controller
public class PartidoController {
	
	private RepositorioPartidoImpl repositorioPartidoDAO;
	
	@RequestMapping("/home")
	public ModelAndView irAHome() {

		ModelMap modelo = new ModelMap();
		
		Partido partido = new Partido();
		modelo.put("partido", partido);
		
		return new ModelAndView("home", modelo);
	}
	
	
	@RequestMapping(value="/mostrar-partidos", method= RequestMethod.GET)
	public ModelAndView mostrarPartidos(){
		
		ModelMap model = new ModelMap();
		
		Partido partido = repositorioPartidoDAO.getAll();
		
		model.put("partido",partido.getCancha()); 
		
		/*if(listaPartidos.size()==3) {
			
			model.put("partidos","hay partidos pero no se como mostrarlos");
		}else {
			
			model.put("partidos","no hay partidos");
		}*/
		return new ModelAndView ("partidos", model);
	}
	
}	
