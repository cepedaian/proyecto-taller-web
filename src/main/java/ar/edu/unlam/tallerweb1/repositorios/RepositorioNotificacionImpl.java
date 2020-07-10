package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelos.Notificacion;
import ar.edu.unlam.tallerweb1.modelos.Partido;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        
        List<Notificacion>listaNotificacionesLazy = new ArrayList();
        
        for(Notificacion notificacion : listaNotificaciones ){
            
            notificacion = session.find(Notificacion.class, notificacion.getId());

    		if (notificacion != null) {
    			// Get Lazy Model
    			Hibernate.initialize(notificacion.getPartido());
    			listaNotificacionesLazy.add(notificacion);
    		}
            
            }
        
        return listaNotificacionesLazy;
    }

    @Override
    public List<Notificacion> getNotificacionesLazyMode(List<Notificacion> notificaciones) {

        final Session session =  sessionFactory.getCurrentSession();
        
        for(Notificacion notificacion : notificaciones ){
        
        notificacion = session.find(Notificacion.class, notificacion.getId());

		if (notificacion != null) {
			// Get Lazy Model
			Hibernate.initialize(notificacion.getPartido());
			notificaciones.add(notificacion);
		}
        
        }

        return notificaciones;
    }



}
