package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelos.Barrio;
import ar.edu.unlam.tallerweb1.modelos.Cancha;



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
	public Long crearCancha(Cancha cancha) {
		
		final Session session = sessionFactory.getCurrentSession();
		Long id = (Long) session.save(cancha);
		return id;
	}

	@Override
	public void eliminarCancha(Long id) {
		final Session session = sessionFactory.getCurrentSession();

		Cancha canchaBuscada = session.get(Cancha.class, id);

		session.delete(canchaBuscada);

	}
	
	@Override
	public Cancha getCancha(Long id) {
		final Session session = sessionFactory.getCurrentSession();

		Cancha canchaBuscada = session.get(Cancha.class, id);

		return canchaBuscada;

	}
	
	
	@Override
	public List<Cancha> buscarCancha(Barrio barrio) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Cancha.class);
        //criteria.createAlias("barrio", "b"); //para determinar el join con Barrio
        criteria.add(Restrictions.eq("barrio.descripcion", "Congreso"));
        List<Cancha> listaCancha = criteria.list();
		
		
		return null;
	}
}
