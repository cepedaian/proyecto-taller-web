package ar.edu.unlam.tallerweb1.controladoresTest;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import ar.edu.unlam.tallerweb1.controladores.UsuarioController;
import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.BarrioService;
import ar.edu.unlam.tallerweb1.servicios.CuentaService;
import ar.edu.unlam.tallerweb1.servicios.UsuarioService;

public class ControladorUsuarioTest {
	
	@Test
	//TEST METODO CREAR-USUARIO DE USUARIOCONTROLLER
	public void verificarQueElControladorNosLlevaALaViewFormUsuario() {
		
		//preparacion
		UsuarioService servicioUsuario = mock(UsuarioService.class);
		BarrioService barrioService = mock(BarrioService.class);
		CuentaService cuentaService = mock(CuentaService.class);
		
		
		UsuarioController usuarioController = new UsuarioController(servicioUsuario,barrioService,cuentaService);
		
		//ejecucion
		final ModelAndView modelandview = usuarioController.CrearUsuario();
		
		//validacion
		assertThat(modelandview.getViewName()).isEqualTo("form-usuario");
	}
	
	@Test
	//TEST METODO INSERTAR-USUARIO DE USUARIOCONTROLLER PROBANDO EMAIL EXISTENTE
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
	
	@Test
	//TEST METODO INSERTAR-USUARIO DE USUARIOCONTROLLER PROBANDO USERNAME EXISTENTE
	public void testQueValidaMensajeDeErrorEnLaVistaCrearUsuarioUserNameExistente() throws Exception{

		UsuarioService servicioUsuario = mock(UsuarioService.class);
		BarrioService barrioService = mock(BarrioService.class);
		CuentaService cuentaService = mock(CuentaService.class);
		Cuenta cuenta = new Cuenta();
		Usuario usuario = new Usuario();
		usuario.setUserName("DiegoSL");
		
		Exception e = new Exception("UserName existente");
		
		doThrow(e).when(cuentaService).crearCuenta(cuenta);
		
		UsuarioController usuarioController = new UsuarioController(servicioUsuario,barrioService,cuentaService);
		
		final ModelAndView modelandview = usuarioController.InsertarUsuario(cuenta, null);
	
		assertThat(modelandview.getViewName()).isEqualTo("form-usuario");
		assertThat(modelandview.getModel()).containsKey("error");
		assertThat(modelandview.getModel().get("error")).isEqualTo("UserName existente");
	}
	
	@Test
	//TEST METODO INVITAR-USUARIO DE USUARIOCONTROLLER
	public void verificarQueElControladorNosLlevaALaViewFormInvitado() {
		
		//preparacion
		UsuarioService servicioUsuario = mock(UsuarioService.class);
		BarrioService barrioService = mock(BarrioService.class);
		CuentaService cuentaService = mock(CuentaService.class);
		
		
		UsuarioController usuarioController = new UsuarioController(servicioUsuario,barrioService,cuentaService);
		
		//ejecucion
		final ModelAndView modelandview = usuarioController.InvitarUsuario();
		
		//validacion
		assertThat(modelandview.getViewName()).isEqualTo("form-invitado");
	}

}
