package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cancha;

public interface CanchaService {
	
	public List<Cancha> getAll();
	
	public Long crearCancha(Cancha cancha);

	public void eliminarCancha(Long id);
}
