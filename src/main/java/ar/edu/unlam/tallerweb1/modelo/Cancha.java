package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity 
public class Cancha {

	 @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String tipo;
	private String tipoSuelo;
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Direccion direccion;
	
	
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
	public Direccion getDireccion() {
		return direccion;
	}
		 
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
}
