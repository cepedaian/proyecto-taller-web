package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorCuenta;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBarrio;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class ControladorUsuarioTest {
	
	@Test
	//TEST METODO CREAR-USUARIO DE USUARIOCONTROLLER
	public void verificarQueElControladorNosLlevaALaViewFormUsuario() {
		
		//preparacion
		ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
		ServicioBarrio servicioBarrio = mock(ServicioBarrio.class);
		ServicioCuenta servicioCuenta = mock(ServicioCuenta.class);
		
		
		ControladorUsuario controladorUsuario = new ControladorUsuario(servicioUsuario, servicioBarrio, servicioCuenta);
		
		//ejecucion
		final ModelAndView modelandview = controladorUsuario.showForm();
		
		//validacion
		assertThat(modelandview.getViewName()).isEqualTo("form-usuario");
	}
	
	@Test
	//TEST METODO INSERTAR-USUARIO DE USUARIOCONTROLLER PROBANDO EMAIL EXISTENTE
	public void testQueValidaMensajeDeErrorEnLaVistaCrearUsuarioEmailExistente() throws Exception{

		ServicioBarrio servicioBarrio = mock(ServicioBarrio.class);
		ServicioCuenta servicioCuenta = mock(ServicioCuenta.class);
		Cuenta cuenta = new Cuenta();
		cuenta.setEmail("ian@hotmail.com");
		
		Exception e = new Exception("Email existente");
		
		doThrow(e).when(servicioCuenta).crearCuenta(cuenta);
		
		ControladorCuenta controladorCuenta = new ControladorCuenta(servicioBarrio, servicioCuenta);
		
		final ModelAndView modelandview = controladorCuenta.insertar(cuenta, null);
	
		assertThat(modelandview.getViewName()).isEqualTo("form-usuario");
		assertThat(modelandview.getModel()).containsKey("error");
		assertThat(modelandview.getModel().get("error")).isEqualTo("Email existente");
	}
	
	@Test
	//TEST METODO INSERTAR-USUARIO DE USUARIOCONTROLLER PROBANDO USERNAME EXISTENTE
	public void testQueValidaMensajeDeErrorEnLaVistaCrearUsuarioUserNameExistente() throws Exception{

		ServicioBarrio servicioBarrio = mock(ServicioBarrio.class);
		ServicioCuenta servicioCuenta = mock(ServicioCuenta.class);
		Cuenta cuenta = new Cuenta();
		Usuario usuario = new Usuario();
		usuario.setUserName("DiegoSL");
		
		Exception e = new Exception("UserName existente");
		
		doThrow(e).when(servicioCuenta).crearCuenta(cuenta);

		ControladorCuenta controladorCuenta = new ControladorCuenta(servicioBarrio, servicioCuenta);

		final ModelAndView modelandview = controladorCuenta.insertar(cuenta, null);
	
		assertThat(modelandview.getViewName()).isEqualTo("form-usuario");
		assertThat(modelandview.getModel()).containsKey("error");
		assertThat(modelandview.getModel().get("error")).isEqualTo("UserName existente");
	}
	
/*	@Test
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
	}*/

}
