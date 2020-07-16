package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelos.Notificacion;
import ar.edu.unlam.tallerweb1.modelos.Usuario;

import java.util.List;

public interface ServicioNotificacion {

    List<Notificacion>getAll();

    void crear(Notificacion notificacion);

    List<Notificacion> getNotificacionesByUsuarioId(Long id);

    List<Notificacion> getNotificacionesLazyMode(List<Notificacion> notificaciones);

    void marcarLeidasByUsuarioId(Long id);
}
