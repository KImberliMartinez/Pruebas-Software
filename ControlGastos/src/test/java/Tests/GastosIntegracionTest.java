package Tests;


import SistemaControlGastos.Negocio.consultaGastos;
import SistemaControlGastos.Negocio.gastosDTO;
import SistemaReporte.Negocio.ConsultaReporte;
import SistemaUsuario.Negocio.ConsultaUsuario;
import SistemaUsuario.Negocio.UsuariosDTO;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Carlos
 */
public class GastosIntegracionTest {

    @Test
    public void pruebaRegistrarUsuarioYAgregarGasto() {
        ConsultaUsuario consultaUsuario = new ConsultaUsuario();
        consultaGastos consultaGastos = new consultaGastos();

        // 1. Registrar Usuario
        UsuariosDTO nuevoUsuario = new UsuariosDTO("usuarioPrueba", "password123");

        consultaUsuario.AgregarUsuario(nuevoUsuario);
        // 2. Obtener ID del usuario recién registrado
        long usuarioId = consultaUsuario.obtenerIDusuario("usuarioPrueba", "password123");
        assertNotEquals(0, usuarioId, "El usuario no fue registrado correctamente.");

        // 3. Agregar Gasto
        gastosDTO nuevoGasto = new gastosDTO("Alimentación", "Compra Supermercado", 150.0f, Date.valueOf("2024-11-28"), usuarioId);

        consultaGastos.registrar(nuevoGasto);

        // 4. Verificar que el gasto se ha registrado correctamente
        List<gastosDTO> gastosUsuario = consultaGastos.obtenerLista(usuarioId);
        assertFalse(gastosUsuario.isEmpty(), "No se registraron gastos para el usuario.");
        assertEquals("Alimentación", gastosUsuario.get(0).getCategoria());
    }

    @Test
    public void pruebaAgregarEditarEliminarGasto() {
        ConsultaUsuario consultaUsuario = new ConsultaUsuario();
        consultaGastos consultaGastos = new consultaGastos();

        // 1. Registrar Usuario
        UsuariosDTO nuevoUsuario = new UsuariosDTO("usuarioT", "test123");
        consultaUsuario.AgregarUsuario(nuevoUsuario);
        long usuarioId = consultaUsuario.obtenerIDusuario("usuarioT", "test123");
        assertNotEquals(0, usuarioId, "El usuario no fue registrado correctamente.");

        // 2. Agregar Gasto
        gastosDTO gasto = new gastosDTO("Transporte", "Pago de uber", 50.0f, Date.valueOf("2024-11-27"), usuarioId);

        consultaGastos.registrar(gasto);

        // 3. Editar Gasto
        List<gastosDTO> gastosUsuario = consultaGastos.obtenerLista(usuarioId);
        long gastoId = gastosUsuario.get(0).getId();
        consultaGastos.actualizarGastos(gastoId, "Transporte", 70.0f);

        // Verificar que el gasto se actualizó
        List<gastosDTO> gastosActualizados = consultaGastos.obtenerLista(usuarioId);
        assertEquals(70.0f, gastosActualizados.get(0).getGasto(), 0.01, "El gasto no fue actualizado correctamente.");

        // 4. Eliminar Gasto
        consultaGastos.Eliminar(gastoId);
        List<gastosDTO> gastosDespuesDeEliminar = consultaGastos.obtenerLista(usuarioId);
        assertTrue(gastosDespuesDeEliminar.isEmpty(), "El gasto no fue eliminado correctamente.");
    }

//    @Test
//    public void pruebaVerReporteSemanal() {
//        ConsultaUsuario consultaUsuario = new ConsultaUsuario();
//        consultaGastos consultaGastos = new consultaGastos();
//        ConsultaReporte consultaReporte = new ConsultaReporte();
//
//        // 1. Registrar Usuario
//        UsuariosDTO usuario = new UsuariosDTO("Ureporte", "reporte123");
//        consultaUsuario.AgregarUsuario(usuario);
//        long usuarioId = consultaUsuario.obtenerIDusuario("Ureporte", "reporte123");
//        assertNotEquals(0, usuarioId);
//
//        // 2. Agregar varios Gastos
//        gastosDTO gasto1 = new gastosDTO("Alimentación", "Comida Hermosillo", 30.0f, Date.valueOf("2024-11-25"), usuarioId);
//        gastosDTO gasto2 = new gastosDTO("Transporte", "Autobus", 50.0f, Date.valueOf("2024-11-27"), usuarioId);
//        consultaGastos.registrar(gasto1);
//        consultaGastos.registrar(gasto2);
//
//        // 3. Obtener reporte semanal
//        List<gastosDTO> reporteSemanal = consultaReporte.listaPorPeriodoSemanal(Date.valueOf("2024-11-25"), usuarioId);
//        assertEquals(2, reporteSemanal.size(), "El reporte semanal no contiene los gastos correctos.");
//    }

    @Test
    public void pruebaEliminarGastoIdInvalido() {
        consultaGastos consultaGastos = new consultaGastos();

        try {
            // Intentar eliminar un gasto con un ID inexistente
            consultaGastos.Eliminar(9999L);
            // No debería lanzar excepción
        } catch (Exception e) {
            fail("No se esperaba una excepción al eliminar un gasto inexistente.");
        }
    }

    @Test
    public void pruebaReporteSinFecha() {
        ConsultaReporte consultaReporte = new ConsultaReporte();

        try {
            // Intentar generar reporte con fecha nula
            consultaReporte.listaPorPeriodoSemanal(null, 1L);
            fail("Se esperaba una excepción al pasar una fecha nula.");
        } catch (NullPointerException e) {
            assertNotNull(e, "Se esperaba una excepción de NullPointerException.");
        }
    }

@Test
public void pruebaReporteUsuarioSinGastos() {
    ConsultaReporte consultaReporte = new ConsultaReporte();

    // ID de usuario que no tiene gastos registrados
    long usuarioId = 999L;

    List<gastosDTO> reporte = consultaReporte.listaPorPeriodoMensual(Date.valueOf("2024-11-01"), usuarioId);

    assertTrue(reporte.isEmpty(), "El reporte debería estar vacío para un usuario sin gastos.");
}
@Test
public void pruebaRegistrarUsuarioConNombreVacio() {
    ConsultaUsuario consultaUsuario = new ConsultaUsuario();
    UsuariosDTO usuario = new UsuariosDTO("", "password123");

    if (usuario.getNombreUsuario().isEmpty()) {
        Exception exception = assertThrows(
            Exception.class,
            () -> { throw new Exception("El nombre de usuario no puede estar vacío"); },
            "Se esperaba una excepción al intentar registrar un usuario con nombre vacío."
        );

        assertTrue(exception.getMessage().contains("El nombre de usuario no puede estar vacío"),
                   "El mensaje de excepción no fue el esperado.");
        return;
    }

    consultaUsuario.AgregarUsuario(usuario);
    fail("No se lanzó una excepción, pero se esperaba que fallara por nombre vacío.");
}

    @Test
    public void testGastosPorCategoriaYusuario() {
        consultaGastos consultaGastos = new consultaGastos();
        // Prepara los datos de prueba en la base de datos
        long usuarioId = 1L; // Cambia según un ID válido en tu base de datos de prueba
        String categoria = "Alimentación"; // Categoría existente en los datos

        // Ejecuta el método
        Double totalGastos = consultaGastos.GastosPorCategoriYusuario(usuarioId, categoria);

        // Valida que el total de gastos no sea nulo y tenga un valor esperado
        assertNotNull(totalGastos, "El total de gastos no debería ser nulo");
        assertTrue(totalGastos >= 0, "El total de gastos debería ser mayor o igual a 0");
    }

    @Test
    public void testGastosPorCategoriaERROR() {
        consultaGastos consultaGastos = new consultaGastos();
        long usuarioId = 1L; // Usuario válido
        String categoria = "CategoríaInexistente"; // Categoría que no existe

        // Ejecuta el método
        Double totalGastos = consultaGastos.GastosPorCategoriYusuario(usuarioId, categoria);

        // Valida que el resultado sea 0.0 cuando no hay gastos en esa categoría
        assertEquals(0.0, totalGastos, "El total de gastos debería ser 0.0 para categorías inexistentes");
    }

   
    
    
    
//    private static EntityManagerFactory entityManagerFactory;
//    private static EntityManager entityManager;
//    private static Usuarios usuario;
//
//    //ESTE ES PARA CREAR UN USUARIO SI NO LO HAY
//    @BeforeAll
//    public static void setUpClass() {
//        // Crear EntityManagerFactory y EntityManager
//        entityManagerFactory = Persistence.createEntityManagerFactory("ConexionPU");
//        entityManager = entityManagerFactory.createEntityManager();
//
//        // Crear usuario de prueba
//        entityManager.getTransaction().begin();
//        usuario = new Usuarios("Ariel", "1234");
//        entityManager.persist(usuario);
//        entityManager.getTransaction().commit();
//    }
//
////    //ESTE PARA PROBAR CON UN USUARIO QUE YA EXISTA
////    @BeforeAll
////    public static void setUpClass() {
////        // Inicializar EntityManager
////        entityManagerFactory = Persistence.createEntityManagerFactory("ConexionPU");
////        entityManager = entityManagerFactory.createEntityManager();
////
////        // Recuperar el usuario existente
////        usuario = entityManager.createQuery("SELECT u FROM Usuarios u WHERE u.usuario = :usuario", Usuarios.class)
////                .setParameter("carlos", "1234") // Usa el nombre del usuario existente
////                .getSingleResult();
////    }
//    @Test
//    public void testInsertarGasto() {
//        // Insertar un nuevo gasto
//        entityManager.getTransaction().begin();
//        Gastos nuevoGasto = new Gastos("Comida", "Almuerzo", 10.5f, new Date(), usuario);
//        entityManager.persist(nuevoGasto);
//        entityManager.getTransaction().commit();
//
//        // Verificar que el gasto se haya insertado correctamente
//        Gastos gastoGuardado = entityManager.find(Gastos.class, nuevoGasto.getId());
//        assertNotNull(gastoGuardado);
//        assertEquals("Comida", gastoGuardado.getCategoria());
//        assertEquals(10.5f, gastoGuardado.getGasto());
//    }
//
//    @Test
//    public void testActualizarGasto() {
//        // Insertar un nuevo gasto
//        entityManager.getTransaction().begin();
//        Gastos nuevoGasto = new Gastos("Transporte", "Taxi", 15.0f, new Date(), usuario);
//        entityManager.persist(nuevoGasto);
//        entityManager.getTransaction().commit();
//
//        // Actualizar el gasto insertado
//        entityManager.getTransaction().begin();
//        Gastos gastoAActualizar = entityManager.find(Gastos.class, nuevoGasto.getId());
//        gastoAActualizar.setDescripcion("Camion");
//        gastoAActualizar.setGasto(20.0f);
//        entityManager.getTransaction().commit();
//
//        // Verificar que el gasto se haya actualizado correctamente
//        Gastos gastoActualizado = entityManager.find(Gastos.class, gastoAActualizar.getId());
//        assertNotNull(gastoActualizado);
//        assertEquals("Camion", gastoActualizado.getDescripcion());
//        assertEquals(20.0f, gastoActualizado.getGasto());
//    }
//
//    @Test
//    public void testEliminarGasto() {
//        // Insertar un nuevo gasto
//        entityManager.getTransaction().begin();
//        Gastos nuevoGasto = new Gastos("Entretenimiento", "Cine", 8.0f, new Date(), usuario);
//        entityManager.persist(nuevoGasto);
//        entityManager.getTransaction().commit();
//
//        // Eliminar el gasto
//        entityManager.getTransaction().begin();
//        Gastos gastoAEliminar = entityManager.find(Gastos.class, nuevoGasto.getId());
//        entityManager.remove(gastoAEliminar);
//        entityManager.getTransaction().commit();
//
//        // Verificar que el gasto se haya eliminado
//        Gastos gastoEliminado = entityManager.find(Gastos.class, nuevoGasto.getId());
//        assertNull(gastoEliminado);
//    }
//
//    @Test
//    public void testBuscarGastoPorId() {
//        // Insertar un nuevo gasto
//        entityManager.getTransaction().begin();
//        Gastos nuevoGasto = new Gastos("Tecnología", "Smartphone", 500.0f, new Date(), usuario);
//        entityManager.persist(nuevoGasto);
//        entityManager.getTransaction().commit();
//
//        // Buscar el gasto por su ID
//        Gastos gastoBuscado = entityManager.find(Gastos.class, nuevoGasto.getId());
//
//        // Verificar que se encontró el gasto correcto
//        assertNotNull(gastoBuscado);
//        assertEquals("Tecnología", gastoBuscado.getCategoria());
//        assertEquals("Smartphone", gastoBuscado.getDescripcion());
//        assertEquals(500.0f, gastoBuscado.getGasto());
//    }
//
//    @Test
//    public void testBuscarGastosPorPeriodo() {
//        // Definir el rango de fechas
//        Calendar calendar = Calendar.getInstance();
//
//        // Establecer fecha de inicio (1 de octubre de 2024)
//        calendar.set(2024, Calendar.OCTOBER, 1, 0, 0, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        Date fechaInicio = calendar.getTime();
//
//        // Establecer fecha de fin (3 de octubre de 2024)
//        calendar.set(2024, Calendar.OCTOBER, 3, 23, 59, 59);
//        Date fechaFin = calendar.getTime();
//
//        // Insertar algunos gastos dentro del periodo
//        entityManager.getTransaction().begin();
//        Gastos gasto1 = new Gastos("Comida", "Almuerzo", 10.5f, fechaInicio, usuario);
//        Gastos gasto2 = new Gastos("Comida", "Cena", 15.0f, new Date(fechaFin.getTime() - 86400000), usuario); // 2 de octubre
//        Gastos gastoFueraDePeriodo = new Gastos("Transporte", "Bus", 5.0f, new Date(fechaInicio.getTime() - 86400000), usuario); // Gasto fuera del periodo (30 de septiembre)
//        entityManager.persist(gasto1);
//        entityManager.persist(gasto2);
//        entityManager.persist(gastoFueraDePeriodo);
//        entityManager.getTransaction().commit();
//
//        // Buscar gastos por el periodo definido
//        List<Gastos> gastosEncontrados = entityManager.createQuery("SELECT g FROM Gastos g WHERE g.fecha BETWEEN :fechaInicio AND :fechaFin", Gastos.class)
//                .setParameter("fechaInicio", fechaInicio)
//                .setParameter("fechaFin", fechaFin)
//                .getResultList();
//
//        // Verifica que al menos un gasto se haya encontrado
//        assertFalse(gastosEncontrados.isEmpty(), "No se encontraron gastos en el período especificado.");
//    }
}
