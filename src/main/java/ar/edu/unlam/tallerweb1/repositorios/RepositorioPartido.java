package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelos.Partido;

public interface RepositorioPartido {
	
	public List<Partido> getAll();

	public void eliminarPartido(Long id);

	public void insertarPartido(Partido partido);

    Partido getById(Long id);
}
