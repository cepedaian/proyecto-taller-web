package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelos.Barrio;
import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBarrio;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;
import ar.edu.unlam.tallerweb1.servicios.ServicioEnviarMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorCuenta {
    private ServicioCuenta servicioCuenta;
    private ServicioBarrio servicioBarrio;
    private ServicioEnviarMail servicioMail;
    
	@Autowired
	public ControladorCuenta(ServicioBarrio servicioBarrio, ServicioCuenta servicioCuenta,ServicioEnviarMail servicioMail) {
		this.servicioBarrio = servicioBarrio;
		this.servicioCuenta = servicioCuenta;
		this.servicioMail = servicioMail;
	}
	

    @RequestMapping(path = "/insertar-usuario", method = RequestMethod.POST) // TEST REALIZADO Y VERIFICADO
	public ModelAndView insertar(@ModelAttribute("cuenta") Cuenta cuenta, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		if(cuenta.getPassword() == "") {
			String mensaje = "Debe completar contrase?a";
			model.put("error", mensaje);
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
		if(cuenta.getUsuario().getFecha_nac() == null) {
			String mensaje = "Debe completar fecha nacimiento";
			model.put("error", mensaje);
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
		if(cuenta.getUsuario().getBarrio() == null) {
			String mensaje = "Debe completar barrio";
			model.put("error", mensaje);
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
		if(cuenta.getUsuario().getPosicion() == "") {
			String mensaje = "Debe completar la posición";
			model.put("error", mensaje);
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
		if(cuenta.getUsuario().getSexo() == "") {
			String mensaje = "Debe completar sexo";
			model.put("error", mensaje);
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
		
		try {
			this.servicioCuenta.crearCuenta(cuenta);
			String mensaje = "Se registro con exito! Recibira un mail de confirmacion de la cuenta.";
			String emailDestinatario = cuenta.getEmail();
			String asunto = "Confirmacion de cuenta";
			String cuerpo = "Te damos la Bienvenida a LineaDeCuatro! "
					+ "Si recibiste este Email, es para confirmar que tu cuenta se creo con exito! "
					+ "Ya podes usar nuestra aplicacion y participar de partidos de futbol con amigos y otras personas.";

			this.servicioMail.enviarMail(emailDestinatario, asunto, cuerpo);
			
			model.put("msj", mensaje);
			return new ModelAndView("home", model);

		} catch (Exception e) {
			model.put("error", e.getMessage());
			List<Barrio> barrios = this.servicioBarrio.getAll();
			model.put("barrios", barrios);
			return new ModelAndView("form-usuario", model);
		}
	}
}
