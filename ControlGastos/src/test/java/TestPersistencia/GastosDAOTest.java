package TestPersistencia;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */


import SistemaControlGastos.Persistencia.DAO.GastosDAO;
import SistemaControlGastos.Persistencia.Entidades.Gastos;
import org.junit.jupiter.api.*;
import org.mockito.*;
import javax.persistence.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Carlos
 */
public class GastosDAOTest {

    @Mock
    private EntityManagerFactory emfMock;
    @Mock
    private EntityManager emMock;
    @Mock
    private EntityTransaction transactionMock;

     private GastosDAO gastosDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(emfMock.createEntityManager()).thenReturn(emMock);
        when(emMock.getTransaction()).thenReturn(transactionMock);
        gastosDAO = new GastosDAO() {
            {
                 emf = emfMock; // Inyectar el mock
            }
        };
    }

    @Test
    void testAgregar() {
        Gastos gasto = new Gastos();

        doNothing().when(emMock).persist(gasto);
        doNothing().when(transactionMock).commit();

        assertDoesNotThrow(() -> gastosDAO.Agregar(gasto));
        verify(emMock).persist(gasto);
        verify(transactionMock).commit();
        verify(emMock).close();
    }

    @Test
    void testAgregarConExcepcion() {
        Gastos gasto = new Gastos();

        doThrow(new RuntimeException("Error simulado")).when(emMock).persist(gasto);

        assertDoesNotThrow(() -> gastosDAO.Agregar(gasto));
        verify(emMock).close();
    }

    @Test
    void testActualizarGastos() {
        doNothing().when(transactionMock).begin();
        doNothing().when(transactionMock).commit();
        Query queryMock = mock(Query.class);

        when(emMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        when(queryMock.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(() -> gastosDAO.actualizarGastos(1L, "Comida", 100.0F));
        verify(transactionMock).commit();
        verify(emMock).close();
    }

    @Test
    void testEliminar() {
        Gastos gastoMock = mock(Gastos.class);

        when(emMock.find(Gastos.class, 1L)).thenReturn(gastoMock);
        doNothing().when(transactionMock).begin();
        doNothing().when(transactionMock).commit();

        assertDoesNotThrow(() -> gastosDAO.Eliminar(1L));
        verify(emMock).remove(gastoMock);
        verify(transactionMock).commit();
        verify(emMock).close();
    }

    @Test
    void testObtenerGastosTotalesPorPeriodo() {
        TypedQuery<Double> queryMock = mock(TypedQuery.class);

        when(emMock.createQuery(anyString(), eq(Double.class))).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(100.0);

        Double result = gastosDAO.obtenerGastosTotalesPorPeriodo(new Date(), new Date());
        assertEquals(100.0, result);
        verify(emMock).close();
    }

    @Test
    void testListaPorPeriodo() {
        List<Gastos> expectedList = new ArrayList<>();
        TypedQuery<Gastos> queryMock = mock(TypedQuery.class);

        when(emMock.createQuery(anyString(), eq(Gastos.class))).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(expectedList);

        List<Gastos> result = gastosDAO.listaPorPeriodo(new Date(), new Date());
        assertEquals(expectedList, result);
        verify(emMock).close();
    }

    @Test
    void testGastosPorUsuario() {
        Query queryMock = mock(Query.class);

        when(emMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(200.0);

        Double result = gastosDAO.GastosPorUusario(1L);
        assertEquals(200.0, result);
        verify(emMock).close();
    }

    @Test
    void testGastosPorCategoriaYUsuario() {
        Query queryMock = mock(Query.class);

        when(emMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(150.0);

        Double result = gastosDAO.GastosPorCategoriYusuario(1L, "Comida");
        assertEquals(150.0, result);
        verify(emMock).close();
    }

    @Test
    void testObtenerUltimoGastoPorUsuario() {
        TypedQuery<Gastos> queryMock = mock(TypedQuery.class);
        Gastos expectedGasto = new Gastos();
        
        when(emMock.createQuery(anyString(), eq(Gastos.class))).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        when(queryMock.setMaxResults(1)).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(expectedGasto);

        Gastos result = gastosDAO.obtenerUltimoGastoPorUsuario(1L);
        assertEquals(expectedGasto, result);
        verify(emMock).close();
    }

    @Test
    void testActualizarGastosConExcepcion() {
        // Simula la creación de la consulta
    when(emMock.createQuery(anyString())).thenThrow(new RuntimeException("Error en actualización"));

    // Ejecutar el método y verificar que no se lanza ninguna excepción
    assertDoesNotThrow(() -> gastosDAO.actualizarGastos(1L, "Comida", 100.0F));

    // Verificar que el EntityManager fue cerrado
    verify(emMock).close();
    }

    @Test
    void testEliminarConExcepcion() {
        // Simula una excepción al buscar el gasto por ID
        when(emMock.find(Gastos.class, 1L)).thenThrow(new RuntimeException("Error al buscar gasto"));

        assertThrows(RuntimeException.class, () -> gastosDAO.Eliminar(1L));
        verify(emMock).close();
    }

    @Test
    void testGastosPorUsuarioConExcepcion() {
        // Simula una excepción en la consulta de gastos por usuario
        when(emMock.createQuery(anyString())).thenThrow(new RuntimeException("Error en la consulta"));

        assertDoesNotThrow(() -> gastosDAO.GastosPorUusario(1L));
        verify(emMock).close();
    }

    @Test
    void testGastosPorCategoriaYUsuarioConExcepcion() {
        // Simula que se lanza una excepción cuando se crea la consulta
    when(emMock.createQuery(anyString())).thenThrow(new RuntimeException("Error en consulta de categoría"));

    // Verificar que no se lanza una excepción al llamar al método
    assertDoesNotThrow(() -> gastosDAO.GastosPorCategoriYusuario(1L, "Comida"));

    // Verificar que el método close() se llama para cerrar el EntityManager
    verify(emMock).close();
    }


}
