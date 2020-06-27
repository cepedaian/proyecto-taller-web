package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dtos.PartidoDTO;
import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.modelos.Partido;
import ar.edu.unlam.tallerweb1.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface ServicioPartido {
	public List<Partido> getAll();
	
	public void eliminarPartido(Long id);

	public void insertarPartido(Partido partido);

    Partido getById(Long id);

	void unirse(Partido partido, Usuario usuario);

	Partido detalleListaUsuarios(Long id);


	String getOrganizador(Partido partido);

}

