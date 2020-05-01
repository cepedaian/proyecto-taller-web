package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.stereotype.Service;

@Service
public class SaludarServicioImpl implements SaludarService{

	@Override
	public String gritar(String nombre) {
		
		
		
		return nombre.toUpperCase() +"dale river";
	}
	
}
