package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorCancha;
import ar.edu.unlam.tallerweb1.modelos.Cancha;
import ar.edu.unlam.tallerweb1.servicios.ServicioBarrio;
import ar.edu.unlam.tallerweb1.servicios.ServicioCancha;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ControladorCanchaTest {

    @Test
    //TEST METODO MOSTRAR-CANCHAS DE CANCHACONTROLLER
    public void verificarQueControladorTeLleveAlViewCanchas(){

        //preparacion
        ServicioCancha servicio = mock(ServicioCancha.class);
        ControladorCancha controlador = new ControladorCancha(servicio,null);

        //ejecucion
        final ModelAndView modelandview = controlador.mostrarCanchas();

        //validacion
        assertThat(modelandview.getViewName()).isEqualTo("canchas");
    }
    @Test
    //TEST METODO SHOW-FORM-CANCHA DE CANCHACONTROLLER
    public void verificarQueControladorTeLleveAlViewFormCancha(){

        //preparacion
        ServicioCancha servicioCancha = mock(ServicioCancha.class);
        ServicioBarrio servicioBarrio = mock(ServicioBarrio.class);
        ControladorCancha controlador = new ControladorCancha(servicioCancha,servicioBarrio);

        //ejecucion
        final ModelAndView modelandview = controlador.showFormCancha();

        //validacion
        assertThat(modelandview.getViewName()).isEqualTo("form-cancha");
    }
    
    @Test
    //TEST METODO CREAR-CANCHA DE CANCHACONTROLLER
    public void verificarQueControladorTeLlevaAlViewCanchas() {
    	
    	//preparacion
        ServicioCancha servicioCancha = mock(ServicioCancha.class);
        ControladorCancha controlador = new ControladorCancha(servicioCancha,null);
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
    	ServicioCancha servicioCancha = mock(ServicioCancha.class);
        ControladorCancha controlador = new ControladorCancha(servicioCancha,null);
        
        //ejecucion
        
        final ModelAndView modelandview = controlador.eliminarCancha(null);
        
        //validacion
        assertThat(modelandview.getViewName()).isEqualTo("cancha-eliminada");
    }
    
}
