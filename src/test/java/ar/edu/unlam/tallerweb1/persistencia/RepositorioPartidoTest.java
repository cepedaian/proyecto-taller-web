package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import antlr.collections.List;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPartidoImpl;

public class RepositorioPartidoTest extends SpringTest {
	

	
	@Test
    @Transactional @Rollback
    public void testInsertarUnPartido(){
        // preparacion
        Partido partido = new Partido();
        Session session = session();
        RepositorioPartidoImpl rp = new RepositorioPartidoImpl(session.getSessionFactory());
       
        //ejecucion
        
        
        rp.insertarPartido(partido);
        
        //comprobacion
        
       
        Partido IdBuscado = session.get(Partido.class, partido.getId());
        assertThat(IdBuscado).isNotNull();
    }

	@Test
    @Transactional @Rollback
    public void testEliminarUnPartido(){
        // preparacion
        Partido partido1 = new Partido();
        Partido partido2 = new Partido();
        Session session = session();
        RepositorioPartidoImpl rp = new RepositorioPartidoImpl(session.getSessionFactory());
       
        //ejecucion
        rp.insertarPartido(partido1);
        rp.insertarPartido(partido2);
        
        rp.eliminarPartido(1L);
        //comprobacion
        
       
        java.util.List<Partido> partidos = rp.getAll();
        
        assertThat(partidos).hasSize(1);
    }


}
