package daos;

import entidades.Licencia;
import entidades.Persona;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author delll
 */
public class LicenciasDAO implements ILicenciasDAO {

    private EntityManagerFactory emf;

    public LicenciasDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    @Override
    public void RegistrarLicencia(Licencia licencia) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Verifica si la persona asociada a la licencia existe en la base de datos
            Persona persona = BuscarPersonaPoRFC(licencia.getRFC());
            if (persona != null) {
                // Asigna la persona encontrada a la licencia
                licencia.setPersona(persona);
                System.out.println("DATOS EN LA BASE DE DATOS");
                em.persist(licencia);
                em.getTransaction().commit();
            } else {
                throw new IllegalArgumentException("La persona con RFC " + licencia.getRFC() + " no existe en la base de datos.");
            }
        } finally {
            em.close();
        }
    }

    @Override
    public Persona BuscarPersonaPoRFC(String rfc) {
        EntityManager em = emf.createEntityManager();
        try{
        return em.createQuery("SELECT p FROM Persona p WHERE p.rfc = :rfc", Persona.class)
                .setParameter("rfc", rfc)
                .getSingleResult();
        }catch (NoResultException ex) {
            // Manejar la excepción cuando no se encuentra ninguna licencia
            System.out.println("No se encontró ninguna licencia para el RFC proporcionado.");
        return null;
    }
    }

    @Override
    public List<Licencia> obtenerLicenciasPorRfc(String rfc) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT l FROM Licencia l WHERE l.persona.rfc = :rfc";
        Query query = em.createQuery(jpql);
        query.setParameter("rfc", rfc);

        return query.getResultList();
    }

    @Override
    public Licencia obtenerLicencia(String rfc) {
        EntityManager em = emf.createEntityManager();
        try {
                   String jpql = "SELECT l FROM Licencia l WHERE l.persona.rfc = :rfc ORDER BY l.id DESC";

        
        TypedQuery<Licencia> query = em.createQuery(jpql, Licencia.class);
        query.setParameter("rfc", rfc);
        query.setMaxResults(1); // Limitar el resultado a una sola licencia
        return query.getSingleResult();
    }
    catch (NoResultException ex) {
            // Manejar la excepción cuando no se encuentra ninguna licencia
            System.out.println("No se encontró ninguna licencia para el RFC proporcionado.");
        return null;
    }
    }

    @Override
    public void actualizarLicencias(long idLicencia,Date nuevaFecha,boolean nuevoEstado) {
         EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            String jpql = "UPDATE Licencia l SET l.FechaVencimiento = :nuevaFecha, l.activa = :nuevoEstado WHERE l.id = :idLicencia";
            int up = em.createQuery(jpql)
                    .setParameter("nuevaFecha", nuevaFecha)
                    .setParameter("nuevoEstado", nuevoEstado)
                    .setParameter("idLicencia", idLicencia)
                    .executeUpdate();

            transaction.commit();
            System.out.println("Se actualizaron " + up + " licencias.");
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();//si la transaccion no termina se devuelve y no guarda nada
            }
            throw e; //exception rollback
        }


    }
    
    @Override
    public List<Licencia> obtenerTodasLasLicencias() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT l FROM Licencia l";
            TypedQuery<Licencia> query = em.createQuery(jpql, Licencia.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
