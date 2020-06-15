package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;

public interface RepositorioCuenta {

    Cuenta findByEmail(String email);

    Cuenta getCuenta(Cuenta cuenta);
}