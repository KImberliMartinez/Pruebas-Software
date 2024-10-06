/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.entidades.Gastos;
import Persistencia.entidades.Usuarios;
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
    public void AgregarUsuario(Usuarios usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            usuario.toString();
            System.out.println("enviado a la bd");
            em.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();

        } finally {
            em.close();

        }
    }

    @Override
    public long obtenerIDusuario(String nombre, String contra) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u.id FROM Usuarios u WHERE u.usuario = :nombre AND u.contra = :contra";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("nombre", nombre);
        query.setParameter("contra", contra);

        long id = 0;
        try {
            id = query.getSingleResult();
        } catch (NoResultException e) {
            // Manejar caso cuando no se encuentra un usuario
            System.out.println("Usuario no encontrado.");

        }

        return id; // Devuelve 0 si no se encuentra el usuario
    }

    @Override
    public long usuarioExistente(String nombre) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u.id FROM Usuarios u WHERE u.usuario = :nombre";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("nombre", nombre);

        long id = 0;
        try {
            id = query.getSingleResult();
        } catch (NoResultException e) {
            // Si no se encuentra el usuario, se devuelve 0
        }

        return id; // Devuelve el ID o 0 si no se encontró
    }

    @Override
    public Usuarios obtenerSoloUusario(long id) {
        EntityManager em = emf.createEntityManager();
        Usuarios usuario = null;

        try {
            // Log para verificar el ID recibido
            System.out.println("Buscando usuario con ID: " + id);

            // Buscar usuario por ID
            usuario = em.find(Usuarios.class, id);

            // Log para verificar el resultado
            if (usuario != null) {
                System.out.println("Usuario encontrado: " + usuario.getUsuario());
            } else {
                System.out.println("Usuario no encontrado.");
            }
        } finally {
            em.close(); // Cerrar el EntityManager
        }

        return usuario; // Devuelve el usuario o null si no se encontró
        }
    @Override
public List<Gastos> listaPorPeriodoYUsuario(Date startDate, Date endDate, long usuarioId) {
    EntityManager em = emf.createEntityManager();

    try {
        em.getTransaction().begin();
        
        // Crear la consulta JPQL para filtrar por fecha y usuario ID
        String jpql = "SELECT g FROM Gastos g WHERE g.fecha BETWEEN :startDate AND :endDate AND g.usuario.id = :usuarioId";
        TypedQuery<Gastos> query = em.createQuery(jpql, Gastos.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("usuarioId", usuarioId);
        
        List<Gastos> resultados = query.getResultList();
        em.getTransaction().commit();
        
        return resultados;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}

    }
