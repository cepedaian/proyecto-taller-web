package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Revista {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String tipo;

    private Integer numeroPublicacion;


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setNumeroPublicacion(Integer numero){
        this.numeroPublicacion = numero;
    }

    public Integer getNumeroPublicacion(){
        return this.numeroPublicacion;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }
}