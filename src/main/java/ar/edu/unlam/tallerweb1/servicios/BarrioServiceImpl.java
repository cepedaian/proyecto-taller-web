package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioBarrio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service ("barrioService")
@Transactional
public class BarrioServiceImpl implements BarrioService{

	private RepositorioBarrio repositorioBarrio;

	@Autowired
	public BarrioServiceImpl(RepositorioBarrio repositorioBarrio) {
		this.repositorioBarrio = repositorioBarrio;
	}

	@Override
	public List<Barrio> getAll() {
		return this.repositorioBarrio.getAll();
	}
}
