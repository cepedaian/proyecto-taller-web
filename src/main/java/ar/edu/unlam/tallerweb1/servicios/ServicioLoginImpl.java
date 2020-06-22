package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
