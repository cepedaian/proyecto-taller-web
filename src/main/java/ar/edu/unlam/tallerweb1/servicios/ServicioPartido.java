package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dtos.PartidoDTO;
import ar.edu.unlam.tallerweb1.modelos.Partido;

import java.util.List;

public interface ServicioPartido {
	public List<Partido> getAll();
	
	public void eliminarPartido(Long id);

	public void insertarPartido(Partido partido);

    Partido getById(Long id);
}
