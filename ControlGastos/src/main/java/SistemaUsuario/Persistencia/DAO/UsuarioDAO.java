/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaUsuario.Persistencia.DAO;

import SistemaUsuario.Persistencia.Entidades.Usuarios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author delll
 */
public class UsuarioDAO implements IUsuarioDAO {
     private EntityManagerFactory emf;

    public UsuarioDAO() {
      emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

      @Override
    public void AgregarUsuario(Usuarios usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Encriptar la contraseña antes de persistir el usuario
            String hashedPassword = BCrypt.hashpw(usuario.getContra(), BCrypt.gensalt());
            usuario.setContra(hashedPassword);  // Establecer la contraseña encriptada
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
    String jpql = "SELECT u FROM Usuarios u WHERE u.usuario = :nombre"; // Ya no se compara la contraseña aquí
    TypedQuery<Usuarios> query = em.createQuery(jpql, Usuarios.class);
    query.setParameter("nombre", nombre);

    long id = 0;
    try {
        Usuarios usuario = query.getSingleResult(); // Obtener el usuario

        // Verificar la contraseña encriptada
        if (usuario != null && BCrypt.checkpw(contra, usuario.getContra())) {
            // Si la contraseña es válida, devolver el ID del usuario
            id = usuario.getId();
        } else {
            System.out.println("Contraseña incorrecta.");
        }

    } catch (NoResultException e) {
        // Manejar caso cuando no se encuentra un usuario
        System.out.println("Usuario no encontrado.");
    } finally {
        em.close();
    }

    return id; // Devuelve 0 si no se encuentra el usuario o si la contraseña es incorrecta
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
    
     // Método para encriptar la contraseña usando BCrypt
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    // Método para verificar la contraseña (al hacer login)
    public boolean verifyPassword(String password, String storedHashedPassword) {
        return BCrypt.checkpw(password, storedHashedPassword);
    }
}
