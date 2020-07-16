package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.modelos.Notificacion;
import ar.edu.unlam.tallerweb1.modelos.Partido;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBarrio;

import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorNotificacion {

    private ServicioUsuario servicioUsuario;
    private ServicioNotificacion servicioNotificacion;

    @Autowired
    public ControladorNotificacion(ServicioUsuario servicioUsuario, ServicioNotificacion servicioNotificacion) {

        this.servicioUsuario = servicioUsuario;
        this.servicioNotificacion = servicioNotificacion;
    }

    @RequestMapping(value = "/mostrar-notificaciones", method = RequestMethod.GET)
    public ModelAndView mostrarNotificaciones(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        ModelMap model = new ModelMap();
        Cuenta cuenta = new Cuenta();
        if (session != null) {
            cuenta = (Cuenta) session.getAttribute("usuario");
            model.put("cuenta", cuenta);
        }

        Usuario usuario = cuenta.getUsuario();

        
        List<Notificacion> notificaciones = this.servicioNotificacion.getNotificacionesByUsuarioId(usuario.getId());
        
        
        
        model.put("notificaciones", notificaciones);

        return new ModelAndView("notificaciones", model);
    }

}
