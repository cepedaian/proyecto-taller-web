package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Cancha;
import ar.edu.unlam.tallerweb1.modelo.Partido;
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
	
	//TEST METODO MOSTRAR-PARTIDO DE PARTIDOCONTROLLER
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

	
	@Test
	//TEST METODO ELIMINAR-PARTIDO DE PARTIDOCONTROLLER
	public void verificarQueControladorTeLleveALaViewEliminarPartido() {
		
		//prearacion
		PartidoService servicioPartido = mock(PartidoService.class);
		CanchaService servicioCancha = mock(CanchaService.class);
		PartidoController controlador = new PartidoController(servicioPartido, servicioCancha);
		
		//ejecucion
		final ModelAndView modelandview = controlador.eliminarPartido(null);
		
		//validacion
		
		assertThat(modelandview.getViewName()).isEqualTo("partido-eliminado");
		
	}
	
	@Test
	//TEST METODO CREAR-PARTIDO DE PARTIDOCONTROLLER
	public void verificarQueControladorTeLleveALaViewCrearPartido() {
		
		//prearacion
		PartidoService servicioPartido = mock(PartidoService.class);
		CanchaService servicioCancha = mock(CanchaService.class);
		PartidoController controlador = new PartidoController(servicioPartido, servicioCancha);
		String user = "pepe";
		//ejecucion
		final ModelAndView modelandview = controlador.crearPartido(user);
		
		//validacion
		
		assertThat(modelandview.getViewName()).isEqualTo("form-partido");
		
	}
	@Test
	//TEST METODO INSERTAR-PARTIDO DE PARTIDOCONTROLLER
	public void verificarQueControladorTeLleveALaViewConfirmarPartido() {

		//prearacion
		PartidoService servicioPartido = mock(PartidoService.class);
		CanchaService servicioCancha = mock(CanchaService.class);
		PartidoController controlador = new PartidoController(servicioPartido, servicioCancha);
		//Cancha cancha = new Cancha();
		//Barrio barrio = new Barrio();
		Partido partido = new Partido();
		//ejecucion
		final ModelAndView modelandview = controlador.insertarPartido(partido, null);

		//validacion

		assertThat(modelandview.getViewName()).isEqualTo("confirmar-partido");

	}
}
