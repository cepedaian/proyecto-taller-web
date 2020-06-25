package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelos.Notificacion;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioNotificaion")
public class RepositorioNotificacionImpl implements RepositorioNotificacion {

    SessionFactory sessionFactory;

    @Autowired
    public RepositorioNotificacionImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Notificacion> getAll() {

        final Session session =  sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Notificacion.class);
        List<Notificacion> notificaciones = criteria.list();

        return notificaciones;
    }
}
