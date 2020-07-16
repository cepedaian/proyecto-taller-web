package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelos.Cuenta;

public interface RepositorioCuenta {

    Cuenta findByEmail(String email);

    Cuenta getCuenta(Cuenta cuenta);

	void crearCuenta(Cuenta cuenta);

	Boolean validarCuentaEmail(Cuenta cuenta);

	String getEmailByIdUsuario(Long id);

	Cuenta getByIdUser(Long id);
}