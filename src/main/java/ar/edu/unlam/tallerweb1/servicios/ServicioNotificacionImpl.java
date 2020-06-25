package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelos.Notificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service ("notificacionService")
@Transactional
public class ServicioNotificacionImpl implements ServicioNotificacion {

    private RepositorioNotificacion repositorioNotificacion;

    @Autowired
    public ServicioNotificacionImpl(RepositorioNotificacion repositorioNotificacion){

        this.repositorioNotificacion = repositorioNotificacion;
    }

    @Override
    public List<Notificacion> getAll() {
        return this.repositorioNotificacion.getAll();
    }
}
