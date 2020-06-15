package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario consultarUsuario (Usuario usuario);
	
	void crearUsuario(Usuario usuario);

	public List<Usuario> buscarUsuario(Usuario usuario);

	Boolean buscarUsuarioByUserName(Usuario usuario);

}
