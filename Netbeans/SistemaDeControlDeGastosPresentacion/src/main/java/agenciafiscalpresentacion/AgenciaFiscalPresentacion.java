package agenciafiscalpresentacion;

import dtos.PersonasDTO;
import entidades.Persona;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author molin
 */
public class AgenciaFiscalPresentacion {

    public static void main(String[] args) {
        System.out.println("Hello World!");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Persona p=new Persona("ssssssssss", "cs", "cs", "cs", "sc", new Date(1998, Calendar.JUNE, 22), "sc");
        em.persist(p);
         em.getTransaction().commit();
    }
}
