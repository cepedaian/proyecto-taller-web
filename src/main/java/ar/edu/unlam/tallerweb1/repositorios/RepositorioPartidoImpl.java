package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Partido;

@Repository("repositorioPartido")
public class RepositorioPartidoImpl implements RepositorioPartido {
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioPartidoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		}
	
	@Override
	public Partido getAll() {
		
		final Session session = sessionFactory.getCurrentSession();
		Partido partido = new Partido();
		
		Partido partidoObtenido = session.get(Partido.class,partido.getId());
		
		
		return partidoObtenido;
	}

}
	