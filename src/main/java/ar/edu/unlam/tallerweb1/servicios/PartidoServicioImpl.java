package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPartido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("partidoService")
@Transactional
public class PartidoServicioImpl implements PartidoService{

	private RepositorioPartido repositorioPartido;

	@Autowired
	public PartidoServicioImpl(RepositorioPartido repositorioPartido){
		this.repositorioPartido = repositorioPartido;
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
}
