package daos;

import Encriptacion.EncriptacionDatos;
import entidades.Persona;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author delll
 */
public class PersonasDAO implements IPersonaDAO {

    private EntityManagerFactory emf;

    public PersonasDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    @Override
    public void RegistrarPersona(Persona persona) {

        //System.out.println("llego");        
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            String telefonoEncriptado = EncriptacionDatos.encriptar(persona.getTelefono());
            persona.setTelefono(telefonoEncriptado);
            
            em.persist(persona);
            persona.toString();
            System.out.println("enviado a la bd");
            em.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();

        } finally {
            em.close();

        }
    }

    @Override
    public List<Persona> obtenerPersonas() {
         EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT p FROM Persona p"; // JPQL para seleccionar todas las personas
        Query query = em.createQuery(jpql);
        return query.getResultList();
       

    }
    
    @Override
    public void insercion(){
         EntityManager em = emf.createEntityManager();
         EntityTransaction transaction = em.getTransaction();
         try {
            transaction.begin();

            // Generar datos falsos para inserción masiva
            List<Persona> personas = generarDatos();

            // Inserción masiva de registros
            for (Persona persona : personas) {
                em.persist(persona);
            }

            transaction.commit();
            System.out.println("Inserción masiva exitosa.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    //auxiliar para crear las personas random
     private static List<Persona> generarDatos() {
        List<Persona> personas = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            Persona persona = new Persona();
            persona.setNombre("Nombre" + i);
            persona.setApellidoP("ApellidoP" + i);
            persona.setApellidoM("ApellidoM" + i);
            persona.setCurp("MAIS589647KLA" + i);
            persona.setTelefono("6442520352");
            persona.setFechaNacimiento( generateRandomDate());
            persona.setRfc("MAERT14"+i); // Edad entre 18 y 97 años

            personas.add(persona);
        }

        return personas;
    }
    
     private static Date generateRandomDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, randBetween(1970, 2004));
        calendar.set(Calendar.MONTH, randBetween(0, 11));
        calendar.set(Calendar.DAY_OF_MONTH, randBetween(1, 28)); // Para simplificar, asumimos que todos los meses tienen máximo 28 días
        return calendar.getTime();
    }

    // Método auxiliar para generar números aleatorios dentro de un rango
    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    } 

    @Override
    public void actualizarPersona(String rfc,String nom,String ApellidoP,String ApellidoM,String tel,String curp,Date fechaN) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

           // String jpql = "UPDATE Persona p SET p.nombre = :nom, p.apellidoP = :ApellidoP,p.apellidoM = :ApellidoM, p.telefono= = :tel, p.curp= = :curp,p.fechaNacimiento= = :fechaN WHERE p.rfc = :rfc";
            String jpql = "UPDATE Persona p SET p.nombre = :nom, p.apellidoP = :ApellidoP, p.apellidoM = :ApellidoM, p.telefono = :tel, p.curp = :curp, p.fechaNacimiento = :fechaN WHERE p.rfc = :rfc";
           int up = em.createQuery(jpql)
                     .setParameter("nom", nom)
                     .setParameter("ApellidoP", ApellidoP)
                     .setParameter("ApellidoM", ApellidoM)
                     .setParameter("tel", tel)
                     .setParameter("curp", curp)
                    .setParameter("fechaN", fechaN)
                    .setParameter("rfc", rfc)
                    .executeUpdate();

            transaction.commit();
            System.out.println("Se actualizo " + up + " la persona.");
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();//si la transaccion no termina se devuelve y no guarda nada
                // throw new IllegalArgumentException("Esta realizando una accion incorrecta.");
            }
            throw e; //exception rollback
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
}
