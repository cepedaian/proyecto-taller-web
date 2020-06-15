package ar.edu.unlam.tallerweb1.servicioTest;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCuenta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.servicios.CuentaService;
import ar.edu.unlam.tallerweb1.servicios.CuentaServiceImpl;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServicioCuentaTest {

    @Test(expected= Exception.class)
    public void validarQueSeLanzeUnaExceptionPorMailExistente()throws Exception{
        //preparacion
        RepositorioCuenta repositorioCuenta = mock(RepositorioCuenta.class);
        RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
        CuentaServiceImpl servicioCuenta = new CuentaServiceImpl(repositorioCuenta,repositorioUsuario);
        Cuenta cuenta = new Cuenta();
        cuenta.setEmail("ian@gmail.com");
        doThrow(Exception.class).when(repositorioCuenta).validarCuentaEmail(cuenta);
        //ejecucion

        servicioCuenta.crearCuenta(cuenta);

        //validacion






    }

}