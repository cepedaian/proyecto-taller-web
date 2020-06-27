package ar.edu.unlam.tallerweb1.modelos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_nac;

	private String sexo;

	@OneToOne(fetch=FetchType.EAGER)
	private Barrio barrio;

	private String posicion;

	@ManyToMany(cascade = {CascadeType.ALL},mappedBy = "usuarios")//VER ALL IANO
    private Set<Partido> partidos;

	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "destinatario")
	private List<Notificacion> notificaciones;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Barrio getBarrio() {
		return barrio;
	}
	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public Date getFecha_nac() {
		return fecha_nac;
	}
	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Set<Partido> getPartidos() {
		return partidos;
	}

	public void setPartido(Set<Partido> partidos) {
		this.partidos = partidos;
	}
}
