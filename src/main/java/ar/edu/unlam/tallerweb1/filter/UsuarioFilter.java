package ar.edu.unlam.tallerweb1.filter;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Barrio;

public class UsuarioFilter {
	
	private String userName;
	private List<Barrio> barrios;
	private String posicion;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Barrio> getBarrios() {
		return barrios;
	}
	public void setBarrios(List<Barrio> barrios) {
		this.barrios = barrios;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
	

}
