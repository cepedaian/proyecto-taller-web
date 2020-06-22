package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelos.Barrio;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuarioImpl;

public class RepositorioUsuarioTest extends SpringTest {
	@Test
	@Transactional
	@Rollback
	public void testBuscarUsuarioConFiltro() {
		// preparacion
		Barrio barrio = new Barrio();
		barrio.setDescripcion("boedo");
		
		Usuario usuario = new Usuario();	
		usuario.setBarrio(barrio);
		usuario.setPosicion("delantero");
		
		Session session = session();
		RepositorioUsuarioImpl ru = new RepositorioUsuarioImpl(session.getSessionFactory());
		
		// ejecucion
		session().save(barrio);
		
		ru.crear(usuario);
		
		List<Usuario> usuariosBuscados = ru.buscar(usuario);
		

		// comprobacion

		
		assertThat(usuariosBuscados).hasSize(1);
	}

}
