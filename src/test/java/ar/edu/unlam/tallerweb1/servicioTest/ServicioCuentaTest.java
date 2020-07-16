package ar.edu.unlam.tallerweb1.servicioTest;

import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCuenta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuentaImpl;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServicioCuentaTest {

    @Test(expected= Exception.class)
    public void testQueLanceUnaExepcionAlcrearCuentaEmailOPassExistente()throws Exception{
        //preparacion
        RepositorioCuenta repositorioCuenta = mock(RepositorioCuenta.class);
        RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
        
        Usuario usuario = new Usuario();
        usuario.setUserName("ninja");
        
        Cuenta cuenta = new Cuenta();
        cuenta.setEmail("ian@gmail.com");
        
        cuenta.setUsuario(usuario);
        
        when(repositorioCuenta.validarCuentaEmail(cuenta)).thenReturn(false);
        when(repositorioUsuario.buscarByUserName(cuenta.getUsuario())).thenReturn(false);
        
        ServicioCuentaImpl servicioCuenta = new ServicioCuentaImpl(repositorioCuenta,repositorioUsuario);
        
        
        //ejecucion

        servicioCuenta.crearCuenta(cuenta);
       
    }
   

}
