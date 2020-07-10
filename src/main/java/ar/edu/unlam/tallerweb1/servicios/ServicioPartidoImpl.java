package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dtos.PartidoDTO;
import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.modelos.Partido;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPartido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("partidoService")
@Transactional
public class ServicioPartidoImpl implements ServicioPartido {

	private RepositorioPartido repositorioPartido;
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public ServicioPartidoImpl(RepositorioPartido repositorioPartido, RepositorioUsuario repositorioUsuario) {
		this.repositorioPartido = repositorioPartido;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public List<Partido> getAll() {
		return repositorioPartido.getAll();
	}

	@Override
	public void eliminarPartido(Long id) {
		repositorioPartido.eliminarPartido(id);
	}

	@Override
	public void insertarPartido(Partido partido) {
		repositorioPartido.insertarPartido(partido);
	}

	@Override
	public Partido getById(Long id) {
		Partido partidoBuscado = repositorioPartido.getById(id);

		/*
		 * PartidoDTO partido = new PartidoDTO();
		 * partido.setFecha(partidoBuscado.getFecha());
		 * partido.setCancha(partidoBuscado.getCancha());
		 * partido.setCantidadJugadores(partidoBuscado.getCantidadJugadores());
		 * partido.setOrganizador(partidoBuscado.getOrganizador());
		 * partido.setSexo(partidoBuscado.getOrganizador());
		 * partido.setUsuarios(partidoBuscado.getJugadores());
		 */

		return partidoBuscado;
	}

	@Override
	public void unirse(Partido partido,Usuario usuario){
		
		this.repositorioPartido.unirse(partido,usuario);
	
	
	}

	@Override
	public Partido getByIdLazyMode(Long id) {
		
		return repositorioPartido.getPartidoLazyMode(id);
		
		
	}
	
	
	

	@Override
	public String getOrganizador(Partido partido){

		return this.repositorioPartido.getOrganizador(partido);
	}

	@Override
	public void bajarse(Partido partido, Usuario usuario) {
		
		this.repositorioPartido.bajarse(partido,usuario);
		
	}

	@Override
	public void eliminarParticipante(Long id_usuario,Long id_partido) {
		this.repositorioPartido.eliminarParticipante(id_usuario,id_partido);
		
	}

}
