package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
//@Table("RESTOS")
public class Revista {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(nullable = false)
    private String nombreRevista;

  
    private String tipoRevista;

   
    private Integer numeroPublicacion;


    public void setNombre(String nombre) {
        this.nombreRevista = nombre;
    }

    public String getNombre(){
        return nombreRevista;
    }

    public void setTipoRevista(String tipoRevista) {
        this.tipoRevista = tipoRevista;
    }

    public String getTipoRevista(){
        return this.tipoRevista;
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