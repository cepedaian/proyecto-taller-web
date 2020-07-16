package ar.edu.unlam.tallerweb1.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCuenta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("cuentaService")
@Transactional
public class ServicioCuentaImpl implements ServicioCuenta {

	private RepositorioCuenta repositorioCuenta;
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public ServicioCuentaImpl(RepositorioCuenta repositorioCuenta, RepositorioUsuario repositorioUsuario) {
		this.repositorioCuenta = repositorioCuenta;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public void crearCuenta(Cuenta cuenta) throws Exception {
		if (this.repositorioCuenta.validarCuentaEmail(cuenta) == false) {
			throw new Exception("Email existente");
		}
		if (this.repositorioUsuario.buscarByUserName(cuenta.getUsuario()) == false) {

			throw new Exception("UserName existente");
		} else {
			this.repositorioCuenta.crearCuenta(cuenta);
		}
	}

	@Override
	public String getEmailByIdUsuario(Long id) {
		return this.repositorioCuenta.getEmailByIdUsuario(id); 
	}

	@Override
	public Cuenta getByIdUser(Long id) {
		return this.repositorioCuenta.getByIdUser(id);
	}

}
