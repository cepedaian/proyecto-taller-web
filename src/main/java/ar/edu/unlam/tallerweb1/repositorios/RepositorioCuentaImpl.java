package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioUsuario")
public class RepositorioCuentaImpl implements RepositorioCuenta {

	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioCuentaImpl(SessionFactory sessionFactory){
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
}
