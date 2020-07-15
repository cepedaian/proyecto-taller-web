package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelos.Partido;
import ar.edu.unlam.tallerweb1.modelos.Usuario;

public interface RepositorioPartido {

	public List<Partido> getAll();

	public void eliminarPartido(Long id);

	public void insertarPartido(Partido partido);

	Partido getById(Long id);

	public void unirse(Partido partido, Usuario usuario);
	
	public void bajarse(Partido partido, Usuario usuario);
	
	Partido getPartidoLazyMode(Long id);

	String getOrganizador(Partido partido);
	
	void eliminarParticipante(Long id_usuario,Long id_partido);
}
