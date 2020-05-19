package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaDelete;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Revista;

@Repository("repositorioPartido")
public class RepositorioPartidoImpl implements RepositorioPartido {
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioPartidoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		}
	
	@Override
	public List<Partido> getAll() {
		
		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Partido.class);
		List<Partido> partidos = criteria.list();

		return partidos;
	}
	
	@Override
	public void eliminarPartido(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		
		Partido partidoBuscado = session.get(Partido.class, id);
	
		session.delete(partidoBuscado);
		
	}

	@Override
	public void insertarPartido(Partido partido) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.save(partido);
		
	}
}
	