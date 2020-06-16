package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.controladores.CanchaController;
import ar.edu.unlam.tallerweb1.modelo.Cancha;
import ar.edu.unlam.tallerweb1.servicios.BarrioService;
import ar.edu.unlam.tallerweb1.servicios.CanchaService;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ControladorCanchaTest {

    @Test
    //TEST METODO MOSTRAR-CANCHAS DE CANCHACONTROLLER
    public void verificarQueControladorTeLleveAlViewCanchas(){

        //preparacion
        CanchaService servicio = mock(CanchaService.class);
        CanchaController controlador = new CanchaController(servicio,null);

        //ejecucion
        final ModelAndView modelandview = controlador.mostrarCanchas();

        //validacion
        assertThat(modelandview.getViewName()).isEqualTo("canchas");
    }
    @Test
    //TEST METODO SHOW-FORM-CANCHA DE CANCHACONTROLLER
    public void verificarQueControladorTeLleveAlViewFormCancha(){

        //preparacion
        CanchaService servicioCancha = mock(CanchaService.class);
        BarrioService servicioBarrio = mock(BarrioService.class);
        CanchaController controlador = new CanchaController(servicioCancha,servicioBarrio);

        //ejecucion
        final ModelAndView modelandview = controlador.showFormCancha();

        //validacion
        assertThat(modelandview.getViewName()).isEqualTo("form-cancha");
    }
    
    @Test
    //TEST METODO CREAR-CANCHA DE CANCHACONTROLLER
    public void verificarQueControladorTeLlevaAlViewCanchas() {
    	
    	//preparacion
        CanchaService servicioCancha = mock(CanchaService.class);
        CanchaController controlador = new CanchaController(servicioCancha,null);
        Cancha cancha = new Cancha();
        servicioCancha.crearCancha(cancha);
        
        //ejecucion

        final ModelAndView modelandview = controlador.crearCancha(cancha, null);
        
        //validacion
        assertThat(modelandview.getViewName()).isEqualTo("canchas");
        //assertThat(modelandview.getModel().get("mensaje")).isEqualTo("La cancha se creo con exito!!!"); VERIFICAR QUE EL ID DEL CONTROLADOR SEA DISTINTO DE CERO
    }
    
    @Test
    //TEST METODO ELIMINAR-CANCHA DE CANCHACONTROLLER
    public void verificarQueControladorTeLlevaAlViewCanchaEliminada() {
    	
    	//preparacion
    	CanchaService servicioCancha = mock(CanchaService.class);
        CanchaController controlador = new CanchaController(servicioCancha,null);
        
        //ejecucion
        
        final ModelAndView modelandview = controlador.eliminarCancha(null);
        
        //validacion
        assertThat(modelandview.getViewName()).isEqualTo("cancha-eliminada");
    }
    
}
