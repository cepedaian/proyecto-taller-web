package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Farmacia;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	// Como todo repositorio maneja acciones de persistencia, normalmente estará
	// inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que
		// invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del
		// objeto recibido como parametro
		// uniqueResult da error si se encuentran más de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				/*
				 * .add(Restrictions.eq("email", usuario.getEmail()))
				 * .add(Restrictions.eq("password", usuario.getPassword()))
				 */
				.uniqueResult();
	}

	@Override
	public void crearUsuario(Usuario usuario) {

		final Session session = sessionFactory.getCurrentSession();
		session.save(usuario);

	}

	@Override
	public List<Usuario> buscarUsuario(Usuario usuario) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("posicion", usuario.getPosicion()));
		criteria.add(Restrictions.eq("barrio", usuario.getBarrio()));

		List<Usuario> usuarios = criteria.list();

		return usuarios;
	}

	@Override
	public List<Usuario> buquedaUsuarioByUserName(Usuario usuario) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("userName", usuario.getUserName()));

		List<Usuario> u = criteria.list();

		return u;
	}

	@Override
	public Boolean buscarUsuarioByUserName(Usuario usuario) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("userName", usuario.getUserName()));

		Usuario u = (Usuario) criteria.uniqueResult();

		if (u == null) {
			return true;
		} else {
			return false;
		}
	}

}
