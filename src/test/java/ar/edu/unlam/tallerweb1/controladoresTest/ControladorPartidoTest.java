/*package ar.edu.unlam.tallerweb1.controladoresTest;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorPartido;
import ar.edu.unlam.tallerweb1.servicios.ServicioBarrio;
import ar.edu.unlam.tallerweb1.servicios.ServicioCancha;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ControladorPartidoTest {

	@Test

	// TEST METODO MOSTRAR-PARTIDO DE PARTIDOCONTROLLER
	public void verificarQueControladorTeLleveALaViewPartidos() {

		// preparacion

		ServicioPartido servicioPartido = mock(ServicioPartido.class);
		ServicioCancha servicioCancha = mock(ServicioCancha.class);
		ServicioNotificacion servicioNotificacion = mock(ServicioNotificacion.class);
		ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
		ServicioCuenta servicioCuenta = mock(ServicioCuenta.class);
		ServicioBarrio servicioBarrio = mock(ServicioBarrio.class);

		ControladorPartido controlador = new ControladorPartido(servicioPartido, servicioCancha, servicioNotificacion,
				servicioUsuario, servicioCuenta, servicioBarrio);

		// ejecucion

		final ModelAndView modelandview = controlador.mostrarPartidos(null);

		// validacion

		assertThat(modelandview.getViewName()).isEqualTo("partidos");
	}

	@Test
	// TEST METODO DETALLE-PARTIDO DE PARTIDOCONTROLLER
	public void verificarQueControladorDetallePartido() {

		// prearacion
		ServicioPartido servicioPartido = mock(ServicioPartido.class);
		ServicioCancha servicioCancha = mock(ServicioCancha.class);
		ServicioNotificacion servicioNotificacion = mock(ServicioNotificacion.class);
		ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
		ServicioCuenta servicioCuenta = mock(ServicioCuenta.class);
		ServicioBarrio servicioBarrio = mock(ServicioBarrio.class);

		ControladorPartido controlador = new ControladorPartido(servicioPartido, servicioCancha, servicioNotificacion,
				servicioUsuario, servicioCuenta, servicioBarrio);
		
		
		// ejecucion
		
		
		
	
		
		final ModelAndView modelandview = controlador.verDetalle(id, null);
	
		assertThat(modelandview.getViewName()).isEqualTo("form-usuario");
		assertThat(modelandview.getModel()).containsKey("error");
		assertThat(modelandview.getModel().get("error")).isEqualTo("Email existente");
		final ModelAndView modelandview = controlador.crearPartido(null);

		// validacion

		assertThat(modelandview.getViewName()).isEqualTo("form-partido");

	}

	
}*/
