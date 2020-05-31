package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Cancha;

public interface RepositorioCancha {
	
	public List<Cancha> getAll();
	
	public Long crearCancha(Cancha cancha);

    public void eliminarCancha(Long id);

    public List<Cancha> buscarCancha(Barrio barrio);

	public Cancha getCancha(Long id);
}
