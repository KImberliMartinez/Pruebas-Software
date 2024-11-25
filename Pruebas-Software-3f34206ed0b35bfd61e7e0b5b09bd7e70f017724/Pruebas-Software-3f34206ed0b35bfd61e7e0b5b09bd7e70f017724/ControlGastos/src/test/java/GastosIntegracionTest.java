
import SistemaControlGastos.Persistencia.Entidades.Gastos;
import SistemaUsuario.Persistencia.Entidades.Usuarios;
import java.util.Calendar;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class GastosIntegracionTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static Usuarios usuario;

    //ESTE ES PARA CREAR UN USUARIO SI NO LO HAY
    @BeforeAll
    public static void setUpClass() {
        // Crear EntityManagerFactory y EntityManager
        entityManagerFactory = Persistence.createEntityManagerFactory("ConexionPU");
        entityManager = entityManagerFactory.createEntityManager();

        // Crear usuario de prueba
        entityManager.getTransaction().begin();
        usuario = new Usuarios("Ariel", "1234");
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
    }

//    //ESTE PARA PROBAR CON UN USUARIO QUE YA EXISTA
//    @BeforeAll
//    public static void setUpClass() {
//        // Inicializar EntityManager
//        entityManagerFactory = Persistence.createEntityManagerFactory("ConexionPU");
//        entityManager = entityManagerFactory.createEntityManager();
//
//        // Recuperar el usuario existente
//        usuario = entityManager.createQuery("SELECT u FROM Usuarios u WHERE u.usuario = :usuario", Usuarios.class)
//                .setParameter("carlos", "1234") // Usa el nombre del usuario existente
//                .getSingleResult();
//    }
    @Test
    public void testInsertarGasto() {
        // Insertar un nuevo gasto
        entityManager.getTransaction().begin();
        Gastos nuevoGasto = new Gastos("Comida", "Almuerzo", 10.5f, new Date(), usuario);
        entityManager.persist(nuevoGasto);
        entityManager.getTransaction().commit();

        // Verificar que el gasto se haya insertado correctamente
        Gastos gastoGuardado = entityManager.find(Gastos.class, nuevoGasto.getId());
        assertNotNull(gastoGuardado);
        assertEquals("Comida", gastoGuardado.getCategoria());
        assertEquals(10.5f, gastoGuardado.getGasto());
    }

    @Test
    public void testActualizarGasto() {
        // Insertar un nuevo gasto
        entityManager.getTransaction().begin();
        Gastos nuevoGasto = new Gastos("Transporte", "Taxi", 15.0f, new Date(), usuario);
        entityManager.persist(nuevoGasto);
        entityManager.getTransaction().commit();

        // Actualizar el gasto insertado
        entityManager.getTransaction().begin();
        Gastos gastoAActualizar = entityManager.find(Gastos.class, nuevoGasto.getId());
        gastoAActualizar.setDescripcion("Camion");
        gastoAActualizar.setGasto(20.0f);
        entityManager.getTransaction().commit();

        // Verificar que el gasto se haya actualizado correctamente
        Gastos gastoActualizado = entityManager.find(Gastos.class, gastoAActualizar.getId());
        assertNotNull(gastoActualizado);
        assertEquals("Camion", gastoActualizado.getDescripcion());
        assertEquals(20.0f, gastoActualizado.getGasto());
    }

    @Test
    public void testEliminarGasto() {
        // Insertar un nuevo gasto
        entityManager.getTransaction().begin();
        Gastos nuevoGasto = new Gastos("Entretenimiento", "Cine", 8.0f, new Date(), usuario);
        entityManager.persist(nuevoGasto);
        entityManager.getTransaction().commit();

        // Eliminar el gasto
        entityManager.getTransaction().begin();
        Gastos gastoAEliminar = entityManager.find(Gastos.class, nuevoGasto.getId());
        entityManager.remove(gastoAEliminar);
        entityManager.getTransaction().commit();

        // Verificar que el gasto se haya eliminado
        Gastos gastoEliminado = entityManager.find(Gastos.class, nuevoGasto.getId());
        assertNull(gastoEliminado);
    }

    @Test
    public void testBuscarGastoPorId() {
        // Insertar un nuevo gasto
        entityManager.getTransaction().begin();
        Gastos nuevoGasto = new Gastos("Tecnología", "Smartphone", 500.0f, new Date(), usuario);
        entityManager.persist(nuevoGasto);
        entityManager.getTransaction().commit();

        // Buscar el gasto por su ID
        Gastos gastoBuscado = entityManager.find(Gastos.class, nuevoGasto.getId());

        // Verificar que se encontró el gasto correcto
        assertNotNull(gastoBuscado);
        assertEquals("Tecnología", gastoBuscado.getCategoria());
        assertEquals("Smartphone", gastoBuscado.getDescripcion());
        assertEquals(500.0f, gastoBuscado.getGasto());
    }

    @Test
    public void testBuscarGastosPorPeriodo() {
        // Definir el rango de fechas
        Calendar calendar = Calendar.getInstance();

        // Establecer fecha de inicio (1 de octubre de 2024)
        calendar.set(2024, Calendar.OCTOBER, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date fechaInicio = calendar.getTime();

        // Establecer fecha de fin (3 de octubre de 2024)
        calendar.set(2024, Calendar.OCTOBER, 3, 23, 59, 59);
        Date fechaFin = calendar.getTime();

        // Insertar algunos gastos dentro del periodo
        entityManager.getTransaction().begin();
        Gastos gasto1 = new Gastos("Comida", "Almuerzo", 10.5f, fechaInicio, usuario);
        Gastos gasto2 = new Gastos("Comida", "Cena", 15.0f, new Date(fechaFin.getTime() - 86400000), usuario); // 2 de octubre
        Gastos gastoFueraDePeriodo = new Gastos("Transporte", "Bus", 5.0f, new Date(fechaInicio.getTime() - 86400000), usuario); // Gasto fuera del periodo (30 de septiembre)
        entityManager.persist(gasto1);
        entityManager.persist(gasto2);
        entityManager.persist(gastoFueraDePeriodo);
        entityManager.getTransaction().commit();

        // Buscar gastos por el periodo definido
        List<Gastos> gastosEncontrados = entityManager.createQuery("SELECT g FROM Gastos g WHERE g.fecha BETWEEN :fechaInicio AND :fechaFin", Gastos.class)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();

        // Verifica que al menos un gasto se haya encontrado
        assertFalse(gastosEncontrados.isEmpty(), "No se encontraron gastos en el período especificado.");
    }

}
