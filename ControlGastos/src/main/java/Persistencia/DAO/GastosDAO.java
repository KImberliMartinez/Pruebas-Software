/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.entidades.Gastos;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
    public List<Gastos> obtenerLista() {
          EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT g FROM Gastos g"; // JPQL para seleccionar todas las personas
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public void actualizarGastos(long id,String categoria, String descripcion, Float gasto) {
            EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

           // String jpql = "UPDATE Persona p SET p.nombre = :nom, p.apellidoP = :ApellidoP,p.apellidoM = :ApellidoM, p.telefono= = :tel, p.curp= = :curp,p.fechaNacimiento= = :fechaN WHERE p.rfc = :rfc";
            
String jpql = "UPDATE Gastos g SET g.Categoria = :categoria, g.Descripcion = :descripcion, g.Gasto = :gasto WHERE g.id = :id";
        
        int up = em.createQuery(jpql)
                   .setParameter("categoria", categoria)
                   .setParameter("descripcion", descripcion) // Corregido el nombre del parámetro
                   .setParameter("gasto", gasto)
                   .setParameter("id", id)
                   .executeUpdate();
            transaction.commit();
            System.out.println("Se actualizo con exito " + up + ".");
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();//si la transaccion no termina se devuelve y no guarda nada
                // throw new IllegalArgumentException("Esta realizando una accion incorrecta.");
            }
            throw e; //exception rollback
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
    
}
