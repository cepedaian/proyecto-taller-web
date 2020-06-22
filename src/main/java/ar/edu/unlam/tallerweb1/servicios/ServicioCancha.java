package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelos.Cancha;

public interface ServicioCancha {
	
	public List<Cancha> getAll();
	
	public Long crearCancha(Cancha cancha);

	public void eliminarCancha(Long id);

	public Cancha getCancha(Long id);
}
