package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioCuenta")
public class RepositorioCuentaImpl implements RepositorioCuenta {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioCuentaImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Cuenta findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Cuenta.class);
		criteria.add(Restrictions.eq("email", email));

		Cuenta cuenta = (Cuenta) criteria.uniqueResult();

		return cuenta;
	}

	@Override
	public Cuenta getCuenta(Cuenta cuenta) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Cuenta.class);
		criteria.add(Restrictions.eq("email", cuenta.getEmail()));
		criteria.add(Restrictions.eq("password", cuenta.getPassword()));

		Cuenta cuentaBuscada = (Cuenta) criteria.uniqueResult();

		return cuentaBuscada;
	}

	@Override
	public void crearCuenta(Cuenta cuenta) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(cuenta);
	}

	@Override
	public Boolean validarCuentaEmail(Cuenta cuenta) {
		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Cuenta.class);
		criteria.add(Restrictions.eq("email", cuenta.getEmail()));

		Cuenta cuentaBuscada = (Cuenta) criteria.uniqueResult();

		return (cuentaBuscada == null);
	}

	@Override
	public String getEmailByIdUsuario(Long id) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Cuenta.class);
		criteria.add(Restrictions.eq("usuario.id", id));
		

		Cuenta cuentaBuscada = (Cuenta) criteria.uniqueResult();

		return cuentaBuscada.getEmail();
	
	}
}
