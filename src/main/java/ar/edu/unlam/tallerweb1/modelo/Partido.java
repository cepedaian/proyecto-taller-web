package ar.edu.unlam.tallerweb1.modelo;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Partido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Integer cantidadJugadores;
	@OneToOne(fetch=FetchType.EAGER)
	private Cancha cancha;
	private String organizador;

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
	
	
	
}
