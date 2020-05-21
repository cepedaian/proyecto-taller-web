package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Partido;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Cancha;



@Repository("repositorioCancha")
public class RepositorioCanchaImpl implements RepositorioCancha{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioCanchaImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Cancha> getAll(){
		
		final Session session =  sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Cancha.class);
		List<Cancha> canchas = criteria.list();
		
		return canchas;
	}
	
	@Override
	public void crearCancha(Cancha cancha) {
		
		final Session session = sessionFactory.getCurrentSession();
		session.save(cancha);
	}

	@Override
	public void eliminarCancha(Long id) {
		final Session session = sessionFactory.getCurrentSession();

		Cancha canchaBuscada = session.get(Cancha.class, id);

		session.delete(canchaBuscada);

	}
}
