package ar.edu.unlam.tallerweb1.persistencia;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

public class FarmaciaTest extends SpringTest {

    final Session preparacion(){
        Farmacia farmacia1 = new Farmacia();
        farmacia1.setNombre("Farmacity");
        farmacia1.setTurno("Domingo");
        farmacia1.setCantEmpleados(10);
        Barrio barrio1 = new Barrio();
        barrio1.setDescripcion("Congreso");
        farmacia1.setBarrio(barrio1);

        Farmacia farmacia2 = new Farmacia();
        farmacia2.setNombre("Farmacity");
        farmacia2.setTurno("Miercoles");
        farmacia2.setCantEmpleados(8);
        Barrio barrio2 = new Barrio();
        barrio2.setDescripcion("Monserrat");
        farmacia2.setBarrio(barrio2);

        Farmacia farmacia3 = new Farmacia();
        farmacia3.setNombre("DrAhorro");
        farmacia3.setTurno("Martes");
        farmacia3.setCantEmpleados(3);
        Barrio barrio3 = new Barrio();
        barrio3.setDescripcion("Congreso");
        farmacia3.setBarrio(barrio3);

        final Session session = session();
        session.save(farmacia1);
        session.save(farmacia2);
        session.save(farmacia3);

        return session;
    }

    @Test
    @Transactional @Rollback
    public void testQueBuscaFarmaciasConNombreFarmacity(){
        //Preparación
        Session session = preparacion();
        //Ejecución
        Criteria criteria = session.createCriteria(Farmacia.class);
        criteria.add(Restrictions.eq("nombre", "Farmacity"));
        List<Farmacia> listaFarmacia = criteria.list();
        //Comprobación
        assertThat(listaFarmacia).hasSize(2);
    }

    @Test
    @Transactional @Rollback
    public void testQueBuscaFarmaciasDeTurnoMartesYMiercoles(){
        //Preparacion
        Session session = preparacion();
        //Ejecución
        Criteria criteria = session.createCriteria(Farmacia.class);
        criteria.add(Restrictions.disjunction()
                        .add(Restrictions.eq("turno","Martes"))
                        .add(Restrictions.eq("turno", "Miercoles"))); //"disjuntion" para tratar el or
        List<Farmacia> listaFarmacia = criteria.list();
        //Comprobación
        assertThat(listaFarmacia).hasSize(2);
    }

    @Test
    @Transactional @Rollback
    public void testQueCuentaFarmaciasDeTurnoLosDomingosConMasDe4Empleados(){
        //Preparacion
        Session session = preparacion();
        //Ejecución
        Criteria criteria = session.createCriteria(Farmacia.class);
        criteria.add(Restrictions.eq("turno", "Domingo"));
        criteria.add(Restrictions.ge("cantEmpleados", 4)); //"ge" para tratar mayor igual
        Long cantFarmacias = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult(); //"projection" para hacer un count
        //Comprobación
        assertThat(cantFarmacias).isEqualTo(1);
    }

    @Test
    @Transactional @Rollback
    public void testQueBuscaFarmaciasDelBarrioCongreso(){
        //Preparación
        Session session = preparacion();
        //Ejecución
        Criteria criteria = session.createCriteria(Farmacia.class);
        criteria.createAlias("barrio", "b"); //para determinar el join con Barrio
        criteria.add(Restrictions.eq("b.descripcion", "Congreso"));
        List<Farmacia> listaFarmacia = criteria.list();
        //Comprobación
        assertThat(listaFarmacia).hasSize(2);
    }
}