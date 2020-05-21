package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cancha;

public interface RepositorioCancha {
	
	public List<Cancha> getAll();
	
	public void crearCancha(Cancha cancha);
}
