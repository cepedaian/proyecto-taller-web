package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelos.Barrio;
import ar.edu.unlam.tallerweb1.modelos.Cuenta;
import ar.edu.unlam.tallerweb1.servicios.ServicioBarrio;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorCuenta {
    private ServicioCuenta servicioCuenta;
    private ServicioBarrio servicioBarrio;

	@Autowired
	public ControladorCuenta(ServicioBarrio servicioBarrio, ServicioCuenta servicioCuenta) {
		this.servicioBarrio = servicioBarrio;
		this.servicioCuenta = servicioCuenta;
	}

    @RequestMapping(path = "/insertar-usuario", method = RequestMethod.POST) // TEST REALIZADO Y VERIFICADO
	public ModelAndView insertar(@ModelAttribute("cuenta") Cuenta cuenta, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		try {
			this.servicioCuenta.crearCuenta(cuenta);
			String mensaje = "Se registro con exito!";
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
