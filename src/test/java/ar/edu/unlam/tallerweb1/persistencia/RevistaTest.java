package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Revista;


import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

public class RevistaTest extends SpringTest {

    @Test
    @Transactional @Rollback
    public void insertarUnRevista(){
        // preparacion
        Revista pronto = new Revista();
        pronto.setNombre("Revista");
        pronto.setTipo("Espectaculos");
        pronto.setNumeroPublicacion(1);

        //ejecucion
        final Session session = session();
        session.save(pronto);

        //comprobacion
        Revista IdBuscado = session.get(Revista.class, pronto.getId());
        assertThat(IdBuscado).isNotNull();
    }

    @Test
    @Transactional @Rollback
    public void actualizarUnaRevista(){
        // preparacion
        Revista pronto = new Revista();
        pronto.setNombre("Pronto");
        pronto.setTipo("Espectaculos");
        pronto.setNumeroPublicacion(null);
        
        //ejecucion
        final Session session = session();
        session.save(pronto);
        
        pronto.setNombre("Pronto");
        pronto.setTipo("deportes");
        pronto.setNumeroPublicacion(2);

        session.update(pronto);

        //comprobacion
        Revista publicacionBuscado = session.get(Revista.class, pronto.getId());
        Assert.assertEquals((int)2,(int)publicacionBuscado.getNumeroPublicacion());
    }

    @Test
    @Transactional @Rollback
    public void BorrarUnaRevista(){
        // preparacion
        Revista pronto = new Revista();
        pronto.setNombre("Pronto");
        pronto.setTipo("Espectaculos");
        pronto.setNumeroPublicacion(null);

        //ejecucion
        final Session session = session();
        session.save(pronto);
        session.delete(pronto);

        //comprobacion
        Revista buscado = session.get(Revista.class, pronto.getId());
        assertThat(buscado).isNull();
    }
}