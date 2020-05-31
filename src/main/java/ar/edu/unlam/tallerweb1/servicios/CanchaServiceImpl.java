package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Cancha;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCancha;

@Service ("canchaService")
@Transactional
public class CanchaServiceImpl implements CanchaService{
	
	private RepositorioCancha repositorioCancha;
	
	@Autowired
	public CanchaServiceImpl(RepositorioCancha repositorioCancha) {
		this.repositorioCancha = repositorioCancha;
	}
	
	@Override
	public List<Cancha> getAll(){
		return this.repositorioCancha.getAll();
	}
	
	@Override
	public Long crearCancha(Cancha cancha) {
		return this.repositorioCancha.crearCancha(cancha);
	}

	@Override
	public void eliminarCancha(Long id) {
		this.repositorioCancha.eliminarCancha(id);
	}

	@Override
	public Cancha getCancha(Long id) {
		return this.repositorioCancha.getCancha(id);
	}
}
