package ar.edu.unlam.tallerweb1.modelos;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Partido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Integer cantidadJugadores;

	@ManyToOne(fetch=FetchType.EAGER)
	private Cancha cancha;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date fecha;

	private String sexo;

	private String organizador;

	@ManyToMany
	@JoinTable(
	  name = "usuario_partido",
	  joinColumns = @JoinColumn(name = "partido_id"),
	  inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> usuarios;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCantidadJugadores() {
		return cantidadJugadores;
	}
	public void setCantidadJugadores(Integer cantidadJugadores) {
		this.cantidadJugadores = cantidadJugadores;
	}
	public Cancha getCancha() {
		return cancha;
	}
	public void setCancha(Cancha cancha) {
		this.cancha = cancha;
	}
	public String getOrganizador() {
		return organizador;
	}
	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Set<Usuario> getJugadores() {
		return usuarios;
	}
	public void setJugadores(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
