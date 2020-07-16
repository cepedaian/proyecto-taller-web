package ar.edu.unlam.tallerweb1.modelos;

import javax.persistence.*;

@Entity 
public class Cancha {
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private String suelo;

	private Integer tipo;

	@Column(length = 1000)
	private String mapa;

	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Direccion direccion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSuelo() {
		return suelo;
	}

	public void setSuelo(String suelo) {
		this.suelo = suelo;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public String getMapa() {
		return mapa;
	}

	public void setMapa(String mapa){
		this.mapa = mapa;
	}

}
