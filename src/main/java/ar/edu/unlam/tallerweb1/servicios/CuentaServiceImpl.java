package ar.edu.unlam.tallerweb1.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCancha;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCuenta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("cuentaService")
@Transactional
public class CuentaServiceImpl implements CuentaService {

	private RepositorioCuenta repositorioCuenta;
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public CuentaServiceImpl(RepositorioCuenta repositorioCuenta, RepositorioUsuario repositorioUsuario) {
		this.repositorioCuenta = repositorioCuenta;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public void crearCuenta(Cuenta cuenta) throws Exception {
		if(cuenta.getPassword() == null) {
			throw new Exception("Debe completar contraseña");
		}
		if(cuenta.getUsuario().getFecha_nac() == null) {
			throw new Exception("Debe completar fecha nacimiento");
		}
		if(cuenta.getUsuario().getBarrio() == null) {
			throw new Exception("Debe completar barrio");
		}
		if(cuenta.getUsuario().getPosicion() == null) {
			throw new Exception("Debe completar la posición");
		}
		if(cuenta.getUsuario().getSexo() == null) {
			throw new Exception("Debe completar sexo");
		}
		if (this.repositorioCuenta.validarCuentaEmail(cuenta) == false) {
			throw new Exception("Email existente");
		}
		if (this.repositorioUsuario.buscarUsuarioByUserName(cuenta.getUsuario()) == false) {

			throw new Exception("UserName existente");

		} else {
			this.repositorioCuenta.crearCuenta(cuenta);

		}

	}

}
