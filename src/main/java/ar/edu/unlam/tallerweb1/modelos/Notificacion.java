package ar.edu.unlam.tallerweb1.modelos;

import javax.persistence.*;

@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)//CAMBIAR A LAZY
    private Partido partido;
    //probar mapear del lado de partido
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//CAMBIAR A LAZY VER CACADE ALL
    private Usuario destinatario;

    private String remitente;

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }


    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Long getId() {
        return id;
    }


}
