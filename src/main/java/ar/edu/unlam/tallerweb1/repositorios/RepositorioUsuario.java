package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelos.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario consultar (Usuario usuario);
	
	void crear(Usuario usuario);

	public List<Usuario> buscar(Usuario usuario);

	Boolean buscarByUserName(Usuario usuario);

	List<Usuario> buquedaByUserName(Usuario usuario);

}
