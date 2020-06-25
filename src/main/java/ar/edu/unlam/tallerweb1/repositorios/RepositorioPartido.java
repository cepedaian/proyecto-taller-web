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

    public void unirse(Partido partido,Usuario usuario);
    
    Set<Usuario> usuariosEnPartido(Long id);

    String getOrganizador(Partido partido);
}
