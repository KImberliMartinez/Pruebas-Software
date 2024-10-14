/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaControlGastos.Persistencia.DAO;

import SistemaControlGastos.Persistencia.DAO.IGastosDAO;
import SistemaControlGastos.Persistencia.Entidades.Gastos;
import SistemaUsuario.Persistencia.Entidades.Usuarios;
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
public class GastosDAO implements IGastosDAO {

    private EntityManagerFactory emf;

    public GastosDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");

    }

    @Override
    public void Agregar(Gastos gastos) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(gastos);
            gastos.toString();
            System.out.println("enviado a la bd");
            em.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();

        } finally {
            em.close();

        }
    }

    @Override
    public List<Gastos> obtenerLista(long usuarioId) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT g FROM Gastos g WHERE g.usuario.id = :usuarioId"; // JPQL para seleccionar todas las personas
        Query query = em.createQuery(jpql);
        query.setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }

    @Override
    public void actualizarGastos(long id,String categoria, Float gasto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Crear la consulta JPQL para actualizar el gasto
            String jpql = "UPDATE Gastos g SET g.categoria = :categoria, g.gasto = :gasto WHERE g.id = :id";
            em.createQuery(jpql)
                    .setParameter("categoria", categoria)
                    .setParameter("gasto", gasto)
                    .setParameter("id", id)
                    .executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    @Override
    public void Eliminar(long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            // Buscar el objeto Gasto con el ID especificado
            Gastos gasto = em.find(Gastos.class, id);

            // Verificar si el objeto existe antes de intentar eliminarlo
            if (gasto != null) {
                em.remove(gasto);
                System.out.println("Gasto con ID " + id + " ha sido eliminado con éxito.");
            } else {
                System.out.println("No se encontró el gasto con ID " + id + ".");
            }

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Si la transacción no termina se revierte
            }
            throw e; // Propagar la excepción
        } finally {
            em.close(); // Cerrar el EntityManager
        }

    }

    @Override
    public Double obtenerGastosTotalesPorPeriodo(Date inicio, Date fin) {
        EntityManager em = emf.createEntityManager();
        try {
            // Crear la consulta JPQL para sumar los gastos
            String jpql = "SELECT SUM(g.gasto) FROM Gastos g WHERE g.fecha BETWEEN :inicio AND :fin";
            TypedQuery<Double> query = em.createQuery(jpql, Double.class);
            query.setParameter("inicio", inicio);
            query.setParameter("fin", fin);

            // Ejecutar la consulta y obtener el resultado
            Double totalGastos = query.getSingleResult();

            // Retornar el resultado
            return totalGastos != null ? totalGastos : 0.0;
        } finally {
            em.close();
        }
    }
@Override
    public List<Gastos> listaPorPeriodo(Date startDate, Date endDate) {
        System.out.println(startDate);
        System.out.println(endDate);
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT g FROM Gastos g WHERE g.fecha BETWEEN :startDate AND :endDate";
            TypedQuery<Gastos> query = em.createQuery(jpql, Gastos.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

  

    @Override
    public Double GastosPorUusario(long id) {
    EntityManager em = null;
    Double totalGastos = 0.0; // Inicializa totalGastos como 0.0
    try {
        em = emf.createEntityManager(); 

        String jpql = "SELECT SUM(g.gasto) FROM Gastos g WHERE g.usuario.id = :usuarioId";
        Query query = em.createQuery(jpql);
        query.setParameter("usuarioId", id); 

        // Almacena el resultado en un objeto
        Object result = query.getSingleResult(); // Obtener el resultado como Object

        // Verifica si el resultado es null
        if (result != null) {
            totalGastos = (Double) result; // Asigna el valor si no es null
        } else {
            totalGastos = 0.0; // Asegúrate de que totalGastos sea 0.0 si el resultado es null
        }

    } catch (NoResultException e) {
        // Si no hay resultados, totalGastos se queda como 0.0
        totalGastos = 0.0; 
    } catch (Exception e) {
        e.printStackTrace(); 
    } finally {
        if (em != null) {
            em.close(); 
        }
    }

    return totalGastos; // Regresar el total de gastos
}
    @Override
    public Double GastosPorCategoriYusuario(long id, String categoria) {
    EntityManager em = emf.createEntityManager();
    Double total = 0.0; // Inicializa total como 0.0

    try {
        String jpql = "SELECT SUM(g.gasto) FROM Gastos g WHERE g.usuario.id = :usuarioId AND g.categoria = :categoria";
        Query query = em.createQuery(jpql);
        query.setParameter("usuarioId", id);
        query.setParameter("categoria", categoria);
        
        // Obtén el resultado de la consulta
        Object result = query.getSingleResult();
        
        // Verifica si el resultado es null antes de hacer la conversión
        if (result != null) {
            total = (Double) result; // Asigna el valor si no es null
        } else {
            total = 0.0; // Asegúrate de que total sea 0.0 si el resultado es null
        }
    } catch (Exception e) {
        e.printStackTrace(); // Maneja la excepción como prefieras
    } finally {
        em.close();
    }

    return total; // Devuelve total
    }

 }
