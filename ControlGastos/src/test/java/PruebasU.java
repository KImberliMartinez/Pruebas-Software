/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import SistemaControlGastos.Negocio.consultaGastos;
import SistemaControlGastos.Negocio.gastosDTO;
import SistemaControlGastos.Persistencia.DAO.GastosDAO;
import SistemaControlGastos.Persistencia.Entidades.Gastos;
import SistemaUsuario.Persistencia.DAO.UsuarioDAO;
import SistemaUsuario.Persistencia.Entidades.Usuarios;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import java.sql.Date;
//import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author delll
 */
public class PruebasU {

    private EntityManagerFactory emf;

    public PruebasU() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    private UsuarioDAO usuarioDAO;

    @BeforeEach
    public void setUp() {
        usuarioDAO = new UsuarioDAO();
    }

    
    //Agregar usuario
    @Test
    public void testAgregarUsuario() {
        // Crear un usuario de prueba
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setUsuario("testU2");
        nuevoUsuario.setContra("password123");

        // Agregar el usuario a la base de datos
        usuarioDAO.AgregarUsuario(nuevoUsuario);

        // Verificar que el ID no es 0 (el usuario fue creado y persistido en la base de datos)
        long idUsuario = usuarioDAO.obtenerIDusuario("testU2", "password123");

        // El ID debe ser diferente de 0 si el usuario fue creado correctamente
        assertNotEquals(0, idUsuario, "El ID del usuario debe ser diferente de 0.");
    }

    @Test
    public void testAgregarUsuarioConContraseñaIncorrecta() {
        // Crear un usuario de prueba
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setUsuario("testUIncorrecto2");
        nuevoUsuario.setContra("password123");

        // Agregar el usuario a la base de datos
        usuarioDAO.AgregarUsuario(nuevoUsuario);

        // Intentar obtener el ID con una contraseña incorrecta
        long idUsuario = usuarioDAO.obtenerIDusuario("testUIncorrecto2", "noPassword");

        // El ID debe ser 0 si la contraseña es incorrecta
        assertEquals(0, idUsuario, "El ID del usuario debe ser 0 si la contraseña es incorrecta.");
    }

    
    
    @Test
    public void testAgregarGastoCorrecto() {
        // enviar id del usuario
        Usuarios usuario = new Usuarios();
        usuario.setId(15L);

        // Crear un gasto válido
        gastosDTO gastoDTO = new gastosDTO("Alimentación", "Compra de alimentos", 100.50f, Date.valueOf("2024-11-05"), usuario.getId());


        // Intentar registrar el gasto
        consultaGastos consulta = new consultaGastos();
        consulta.registrar(gastoDTO);

        // Verificar que el gasto fue registrado correctamente
        GastosDAO gastosDAO = new GastosDAO();
        Gastos gastoRegistrado = gastosDAO.obtenerUltimoGastoPorUsuario(usuario.getId());
        assertNotNull(gastoRegistrado, "El gasto debería haberse registrado en la base de datos.");
        assertEquals("Alimentación", gastoRegistrado.getCategoria());
        assertEquals("Compra de alimentos", gastoRegistrado.getDescripcion());
        assertEquals(100.50f, gastoRegistrado.getGasto());
    }

    @Test
    public void testAgregarGastoConUsuarioInvalido() {
        // Crear un gasto con un usuario inválido
        gastosDTO gastoDTO = new gastosDTO("Transporte", "Gasolina", 50.0f, Date.valueOf("2024-11-05"), 999L);
        // Intentar registrar el gasto
        consultaGastos consulta = new consultaGastos();
        consulta.registrar(gastoDTO);

        // Verificar que no se registró el gasto
        GastosDAO gastosDAO = new GastosDAO();
        Gastos gastoRegistrado = gastosDAO.obtenerUltimoGastoPorUsuario(999L);
        assertNull(gastoRegistrado, "No debería haberse registrado un gasto con un usuario inválido.");
    }
     

    @Test
    public void testEliminarGastoExistente() {
        
        long id = 21;

        // Instanciar el DAO y eliminar el gasto
        GastosDAO gastosDAO = new GastosDAO();
        gastosDAO.Eliminar(id);

        // Verificar que el gasto fue eliminado
        EntityManager em = emf.createEntityManager();
        Gastos gasto = em.find(Gastos.class, id);

        // El gasto debe ser nulo, ya que fue eliminado
        assertNull(gasto);

        // Verificar si el mensaje de éxito fue impreso correctamente
        // Puedes utilizar un marco para capturar la salida de la consola o verificar manualmente
    }

    @Test
    public void testEliminarGastoInexistente() {
        // Crear el objeto GastosDAO
        GastosDAO gastosDAO = new GastosDAO();

        // Intentamos eliminar un gasto con un ID que no existe (ID 999)
        gastosDAO.Eliminar(999);

        // Verificamos que no ha ocurrido ningún cambio en la base de datos
        EntityManager em = emf.createEntityManager();
        Gastos gasto = em.find(Gastos.class, 999);
        assertNull(gasto);  // No debería haber un gasto con ID 999
    }
     
    @Test
    public void testActualizarGastoExistente() {
        long id = 22;
        // Crear el objeto GastosDAO
        GastosDAO gastosDAO = new GastosDAO();
        // Llamar al método para actualizar el gasto
        gastosDAO.actualizarGastos(id, "Transporte", 150.0f);
        EntityManager em = emf.createEntityManager();

        // Verificamos que el gasto se ha actualizado
        Gastos actualizado = em.find(Gastos.class, id);
        em.close();
        // Verificamos que el gasto tiene los valores actualizados
        assertNotNull(actualizado);  // El gasto debe existir
        assertEquals("Transporte", actualizado.getCategoria());  // La categoría debe haber cambiado
        assertEquals(150.0f, actualizado.getGasto(), 0.01);  // El gasto debe haber cambiado
    }

    @Test
    public void testActualizarGastoInexistente() {
        long id = 999;
        // Crear el objeto GastosDAO
        GastosDAO gastosDAO = new GastosDAO();

        // Intentamos actualizar un gasto con un ID que no existe (ID 999)
        gastosDAO.actualizarGastos(id, "Transporte", 150.0f);

        // Verificamos que no ha ocurrido ningún cambio en la base de datos
        EntityManager em = emf.createEntityManager();
        Gastos gasto = em.find(Gastos.class, id);
        em.close();
        assertNull(gasto);  // No debería haber un gasto con ID 999
    }
    
    @Test
    public void ReporteGastosCorrecto() {
        // Crear dos gastos y persistirlos en la base de datos
        Usuarios usuario= usuarioDAO.obtenerSoloUusario(21);
        Gastos gasto1 = new Gastos("Comida", "comida viaje 1", 100.0f, Date.valueOf("2024-11-05"), usuario);
      

        Gastos gasto2 = new Gastos("Transporte", "Autobus H", 50.0f, Date.valueOf("2024-11-06"), usuario);
       

        // Crear el objeto GastosDAO
        GastosDAO gastosDAO = new GastosDAO();
        gastosDAO.Agregar(gasto1);
        gastosDAO.Agregar(gasto2);
        // Establecer el periodo (por ejemplo, desde el 1 de noviembre de 2024 hasta el 9 de noviembre de 2024)
        Date inicio = Date.valueOf("2024-11-01");
        Date fin = Date.valueOf("2024-11-09");

        // Obtener el total de los gastos por periodo
        Double totalGastos = gastosDAO.obtenerGastosTotalesPorPeriodo(inicio, fin);
        assertEquals(150.0, totalGastos, 0.01);  // Total debe ser 100.0 + 50.0 = 150.0

        // Obtener la lista de gastos por periodo
        List<Gastos> gastosPorPeriodo = gastosDAO.listaPorPeriodo(inicio, fin);
        assertEquals(2, gastosPorPeriodo.size());  // Debería haber 2 gastos en el periodo
    }

    @Test
    public void ReporteGastosInorrecto() {
        // Crear el objeto GastosDAO
        GastosDAO gastosDAO = new GastosDAO();

        // Establecer un periodo donde no existen gastos (por ejemplo, desde el 1 de diciembre de 2024 hasta el 5 de diciembre de 2024)
        Date inicio = Date.valueOf("2024-12-01");
        Date fin = Date.valueOf("2024-12-05");

        // Obtener el total de los gastos por periodo (no debería haber ninguno)
        Double totalGastos = gastosDAO.obtenerGastosTotalesPorPeriodo(inicio, fin);
        assertEquals(0.0, totalGastos, 0.01);  // El total debe ser 0.0

        // Obtener la lista de gastos por periodo (debe ser vacía)
        List<Gastos> gastosPorPeriodo = gastosDAO.listaPorPeriodo(inicio, fin);
        assertTrue(gastosPorPeriodo.isEmpty());  // La lista debe estar vacía
    }
}
