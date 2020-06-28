package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelos.Cuenta;

public interface ServicioCuenta {
	public void crearCuenta(Cuenta cuenta) throws Exception;
	String getEmailByIdUsuario(Long id);

}
