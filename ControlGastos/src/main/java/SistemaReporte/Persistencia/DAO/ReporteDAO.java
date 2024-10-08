/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaReporte.Persistencia.DAO;

import SistemaControlGastos.Persistencia.Entidades.Gastos;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author delll
 */
public class ReporteDAO implements IReporteDAO {
  private EntityManagerFactory emf;
    public ReporteDAO() {
          emf = Persistence.createEntityManagerFactory("ConexionPU");

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
