package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelos.Usuario;

public interface ServicioUsuario {

	void crear(Usuario usuario);
	List<Usuario> buscar(Usuario usuario);
	Usuario getByUserName(String userName);
}
