package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Barrio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Comuna comuna;
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	public Comuna getComuna() {
		return comuna;
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}
}
