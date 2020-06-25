package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelos.Notificacion;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    @Override
    public void crear(Notificacion notificacion) {
        final Session session =  sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Notificacion.class);
        session.save(notificacion);

    }

    @Override
    public List<Notificacion> getNotificacionesByUsuarioId(Long id) {

        final Session session =  sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Notificacion.class);
        criteria.add(Restrictions.eq("destinatario.id",id));

        List<Notificacion>listaNotificaciones = criteria.list();

        return listaNotificaciones;
    }
}
