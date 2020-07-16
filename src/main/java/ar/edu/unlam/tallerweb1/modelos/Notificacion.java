package ar.edu.unlam.tallerweb1.modelos;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;

@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Partido partido;
    //probar mapear del lado de partido
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Usuario destinatario;

    private String remitente;

    private String asunto;
    
    private String cuerpo;
    
    private Boolean leido = false;
    

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

    public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }
}
