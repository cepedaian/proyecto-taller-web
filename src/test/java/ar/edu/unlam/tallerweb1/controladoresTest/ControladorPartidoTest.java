package ar.edu.unlam.tallerweb1.controladoresTest;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.unlam.tallerweb1.controladores.PartidoController;
import ar.edu.unlam.tallerweb1.servicios.CanchaService;
import ar.edu.unlam.tallerweb1.servicios.PartidoService;

public class ControladorPartidoTest{
	
	@Test
	@Transactional @Rollback
	
	public void verificarQueControladorTeLleveALaViewPartidos() {
		
		//preparacion 
		
		PartidoService servicioPartido = mock(PartidoService.class);
		CanchaService servicioCancha = mock(CanchaService.class);
		PartidoController controlador = new PartidoController(servicioPartido, servicioCancha);
		
		//ejecucion
		
		final ModelAndView modelandview = controlador.mostrarPartidos();
		
		//validacion
		
		assertThat(modelandview.getViewName()).isEqualTo("partidos");
	}

	
}
