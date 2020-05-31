package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface UsuarioService {
	
	void crearUsuario (Usuario usuario);

	List<Usuario> buscarUsuario(Usuario usuario);
}
