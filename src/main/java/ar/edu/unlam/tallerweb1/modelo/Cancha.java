package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Cancha {

	 @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String tipo;
	private String tipoSuelo;
	
	
	public Long getId() {
		return this.id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipoSuelo() {
		return tipoSuelo;
	}
	public void setTipoSuelo(String tipoSuelo) {
		this.tipoSuelo = tipoSuelo;
	}
	
		 
}
