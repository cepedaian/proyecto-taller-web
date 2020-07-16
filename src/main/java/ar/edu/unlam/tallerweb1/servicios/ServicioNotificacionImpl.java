package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelos.Notificacion;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
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

    @Override
    public void crear(Notificacion notificacion) {
        this.repositorioNotificacion.crear(notificacion);
    }
    @Override
    public List<Notificacion> getNotificacionesByUsuarioId(Long id){
        return this.repositorioNotificacion.getNotificacionesByUsuarioId(id);
    }

	@Override
	public List<Notificacion> getNotificacionesLazyMode(List<Notificacion> notificaciones) {
		return this.repositorioNotificacion.getNotificacionesLazyMode(notificaciones);
	}

    @Override
    public void marcarLeidasByUsuarioId(Long id) {
        this.repositorioNotificacion.marcarLeidasByUsuarioId(id);
    }

    @Override
    public Integer getCantNotificacionesNoLeidasByUsuarioId(Long id) {
        return this.repositorioNotificacion.getCantNotificacionesNoLeidasByUsuarioId(id);
    }
}
