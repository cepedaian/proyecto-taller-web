package ar.edu.unlam.tallerweb1.repositorios;

import java.util.*;

import ar.edu.unlam.tallerweb1.dtos.PartidoDTO;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelos.Notificacion;
import ar.edu.unlam.tallerweb1.modelos.Partido;

@Repository("repositorioPartido")
public class RepositorioPartidoImpl implements RepositorioPartido {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioPartidoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Partido> getAll() {
		Calendar c = new GregorianCalendar();

		Date currentDate = c.getTime();

		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Partido.class);
		criteria.add(Restrictions.gt("fecha", currentDate));
		List<Partido> partidos = criteria.list();
		return partidos;
	}

	@Override
	public void eliminarPartido(Long id) {
		final Session session = sessionFactory.getCurrentSession();

		Partido partido = session.find(Partido.class, id);

		
		String hql = "delete from Notificacion where partido.id = " +id;
		Query query = session.createQuery(hql);
		query.executeUpdate();
		
		if (partido != null) {
			// Get Lazy Model
			Hibernate.initialize(partido.getJugadores());
			partido.getJugadores().clear();

		}

		session.delete(partido);

	}

	@Override
	public void insertarPartido(Partido partido) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(partido);
	}

	@Override
	public Partido getById(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Partido partidoBuscado = session.get(Partido.class, id);

		return partidoBuscado;
	}

	public void unirse(Partido partido, Usuario usuario) {

		final Session session = sessionFactory.getCurrentSession();
		Partido partido1 = session.get(Partido.class, partido.getId());

		partido1.getJugadores().add(usuario);
		partido1.restarJugador();
		session.save(partido1);

	}

	public Partido detalleListaUsuarios(Long id) {
		final Session session = sessionFactory.getCurrentSession();

		Partido partido = session.find(Partido.class, id);

		if (partido != null) {
			// Get Lazy Model
			Hibernate.initialize(partido.getJugadores());
		}

		return partido;
	}

	@Override
	public String getOrganizador(Partido partido) {
		final Session session = sessionFactory.getCurrentSession();
		Partido partido2 = session.get(Partido.class, partido.getId());

		return partido2.getOrganizador();
	}

	@Override
	public void bajarse(Partido partido, Usuario usuario) {

		final Session session = sessionFactory.getCurrentSession();
		Partido partido1 = session.get(Partido.class, partido.getId());
		
		Set<Usuario> usuarios = partido1.getJugadores();
		
		for (Usuario user: usuarios) {
			if(user.getUserName().equals(usuario.getUserName())) {
				usuarios.remove(user);
				break;
			}
		
		}	
		
		partido1.sumarJugador();

		session.update(partido1);

	}

	@Override
	public void eliminarParticipante(Long id_usuario,Long id_partido) {
		final Session session = sessionFactory.getCurrentSession();
		Partido partido = session.get(Partido.class, id_partido);
		
		Usuario usuario = session.get(Usuario.class,id_usuario);
		
		Set<Usuario> usuarios = partido.getJugadores();
		
		for (Usuario user: usuarios) {
			if(user.getId().equals(usuario.getId())) {
				usuarios.remove(user);
				break;
			}
		}
		
		partido.sumarJugador();
		
		session.update(partido);
		
	
	}
	



}
