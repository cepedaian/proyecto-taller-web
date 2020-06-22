package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario) {

		this.repositorioUsuario = repositorioUsuario;

	}

	@Override
	public void crear(Usuario usuario) {

		this.repositorioUsuario.crear(usuario);

	}

	@Override
	public List<Usuario> buscar(Usuario usuario) {

		if (usuario.getUserName() != "") {
			List<Usuario> usuarioBuscado = this.repositorioUsuario.buquedaByUserName(usuario);
			return usuarioBuscado;
		} else {
			List<Usuario> usuarioBuscado = this.repositorioUsuario.buscar(usuario);
			return usuarioBuscado;
		}

	}
}
