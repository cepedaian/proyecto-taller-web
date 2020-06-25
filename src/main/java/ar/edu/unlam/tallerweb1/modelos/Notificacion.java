package ar.edu.unlam.tallerweb1.modelos;

import javax.persistence.*;

@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @OneToOne(fetch = FetchType.EAGER)
    private Partido partido;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public Partido getPartido() {
        return partido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
