package ar.edu.unlam.tallerweb1.modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Integer cantidadJugadores;
	private String cancha;
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
	public String getCancha() {
		return cancha;
	}
	public void setCancha(String cancha) {
		this.cancha = cancha;
	}
	public String getOrganizador() {
		return organizador;
	}
	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}
	
	
	
}
