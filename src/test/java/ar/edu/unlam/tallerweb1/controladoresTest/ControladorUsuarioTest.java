package ar.edu.unlam.tallerweb1.controladoresTest;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import ar.edu.unlam.tallerweb1.controladores.UsuarioController;
import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.servicios.BarrioService;
import ar.edu.unlam.tallerweb1.servicios.CuentaService;
import ar.edu.unlam.tallerweb1.servicios.UsuarioService;

public class ControladorUsuarioTest {

	@Test
	public void testQueValidaMensajeDeErrorEnLaVistaCrearUsuarioEmailExistente() throws Exception{

		UsuarioService servicioUsuario = mock(UsuarioService.class);
		BarrioService barrioService = mock(BarrioService.class);
		CuentaService cuentaService = mock(CuentaService.class);
		Cuenta cuenta = new Cuenta();
		cuenta.setEmail("ian@hotmail.com");
		
		Exception e = new Exception("Email existente");
		
		doThrow(e).when(cuentaService).crearCuenta(cuenta);
		
		UsuarioController usuarioController = new UsuarioController(servicioUsuario,barrioService,cuentaService);
		
		final ModelAndView modelandview = usuarioController.InsertarUsuario(cuenta, null);
	
		assertThat(modelandview.getViewName()).isEqualTo("form-usuario");
		assertThat(modelandview.getModel()).containsKey("error");
		assertThat(modelandview.getModel().get("error")).isEqualTo("Email existente");
	}

}
