package daos;

import entidades.Automovil;
import entidades.Licencia;
import entidades.Placa;
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
public class PlacasDAO implements IPlacasDAO {

    private EntityManagerFactory emf;

    public PlacasDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    @Override
    public void AgregarPlacasNuevas(Placa p, Automovil a) {
        //System.out.println("llego");        
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.persist(p);
            a.toString();
            p.toString();
            System.out.println("enviado a la bd");
            em.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();

        } finally {
            em.close();

        }
    }

    @Override
    public void AgregarPlacasUsadas(Placa p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            p.toString();
            System.out.println("enviado a la bd");
            em.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();

        } finally {
            em.close();

        }
    }

    @Override
    public Automovil BuscarPlacas(String p) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT a FROM Automovil a WHERE a.numeroSerie = :p ORDER BY a.id DESC";

            TypedQuery<Automovil> query = em.createQuery(jpql, Automovil.class);
            query.setParameter("p", p);
            query.setMaxResults(1); // Limitar el resultado a un auto
            return query.getSingleResult();
        } catch (NoResultException ex) {
            // Manejar la excepción cuando no se encuentra ninguna licencia
            System.out.println("No se encontró ningun Auto para el numSerie proporcionado.");
            return null;
        }

    }

    @Override
    public List<Placa> obtenerPlacasPorRfc(String rfc) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT p FROM Placa p WHERE p.automovil.persona.rfc = :rfc";

        Query query = em.createQuery(jpql);
        query.setParameter("rfc", rfc);

        return query.getResultList();
    }

    @Override
    public void actualizarPlacas(long id, Date FechaR, boolean nuevoEstado) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            String jpql = "UPDATE Placa p SET p.fechaRecepcion = :FechaR, p.activa = :nuevoEstado WHERE p.id = :id";
            int up = em.createQuery(jpql)
                    .setParameter("FechaR", FechaR)
                    .setParameter("nuevoEstado", nuevoEstado)
                    .setParameter("id", id)
                    .executeUpdate();

            transaction.commit();
            System.out.println("Se actualizo " + up + " la placa.");
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();//si la transaccion no termina se devuelve y no guarda nada
            }
            throw e; //exception rollback
        }

    }
    
    @Override
    public List<Placa> obtenerDetallesPlacas() {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT p FROM Placa p";

        Query query = em.createQuery(jpql);

        return query.getResultList();
    }
}
