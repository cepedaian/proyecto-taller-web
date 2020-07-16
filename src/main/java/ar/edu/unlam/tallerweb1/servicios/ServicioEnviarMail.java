package ar.edu.unlam.tallerweb1.servicios;
import javax.servlet.http.HttpServlet;
public interface ServicioEnviarMail{
	public void enviarMail(String destinatario, String asunto, String cuerpo);
}
