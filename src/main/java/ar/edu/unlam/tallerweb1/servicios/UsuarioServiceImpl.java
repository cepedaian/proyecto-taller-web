package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	public UsuarioServiceImpl(RepositorioUsuario repositorioUsuario) {
		
		this.repositorioUsuario = repositorioUsuario;
		
	}
	@Override
	public void crearUsuario(Usuario usuario) {

		this.repositorioUsuario.crearUsuario(usuario);
		
	}
	@Override
	public List<Usuario> buscarUsuario(Usuario usuario) {

		return this.repositorioUsuario.buscarUsuario(usuario);
	}
}
