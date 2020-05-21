package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Partido;

import java.util.List;

public interface PartidoService {
	public List<Partido> getAll();
	
	public void eliminarPartido(Long id);

	public void insertarPartido(Partido partido);
}
