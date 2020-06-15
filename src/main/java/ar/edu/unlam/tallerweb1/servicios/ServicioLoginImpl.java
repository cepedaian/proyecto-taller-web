package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	private RepositorioCuenta repositorioCuenta;

	@Autowired
	public ServicioLoginImpl(RepositorioCuenta repositorioCuenta){
		this.repositorioCuenta = repositorioCuenta;
	}

	@Override
	public Cuenta getCuenta (Cuenta cuenta) {
		return repositorioCuenta.getCuenta(cuenta);
	}

}
