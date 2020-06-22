package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelos.Barrio;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioBarrio")
public class RepositorioBarrioImpl implements RepositorioBarrio{

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioBarrioImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Barrio> getAll(){
		
		final Session session =  sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Barrio.class);
		List<Barrio> barrios = criteria.list();
		
		return barrios;
	}
}
