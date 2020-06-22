package ar.edu.unlam.tallerweb1.dtos;

import ar.edu.unlam.tallerweb1.modelos.Cancha;
import ar.edu.unlam.tallerweb1.modelos.Usuario;

import java.util.Date;
import java.util.Set;

public class PartidoDTO {
    private Integer cantidadJugadores;
	private Cancha cancha;
	private Date fecha;
	private String sexo;
	private String organizador;
	private Set<Usuario> usuarios;

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

	public String getOrganizador() {
		return organizador;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
