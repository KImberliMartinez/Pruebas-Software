/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import SistemaControlGastos.Negocio.IconsultaGastos;
import SistemaControlGastos.Negocio.consultaGastos;
import SistemaControlGastos.Negocio.gastosDTO;
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

    private IconsultaGastos gastosDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(emfMock.createEntityManager()).thenReturn(emMock);
        when(emMock.getTransaction()).thenReturn(transactionMock);
        gastosDAO = new consultaGastos(){
            {
                EntityManagerFactory emf = emfMock; // Inyectar el mock
            }
        };
    }

    @Test
    void testAgregar() {
        gastosDTO gasto = new gastosDTO();

        doNothing().when(emMock).persist(gasto);
        doNothing().when(transactionMock).commit();

        assertDoesNotThrow(() -> gastosDAO.registrar(gasto));
        verify(emMock).persist(gasto);
        verify(transactionMock).commit();
        verify(emMock).close();
    }

    @Test
    void testAgregarConExcepcion() {
        gastosDTO gasto = new gastosDTO();

        doThrow(new RuntimeException("Error simulado")).when(emMock).persist(gasto);

        assertDoesNotThrow(() -> gastosDAO.registrar(gasto));
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

        List<gastosDTO> result = gastosDAO.listaPorPeriodo(new Date(), new Date());
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
        GastosDAO g= new GastosDAO();
        when(emMock.createQuery(anyString(), eq(Gastos.class))).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        when(queryMock.setMaxResults(1)).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(expectedGasto);

        Gastos result = g.obtenerUltimoGastoPorUsuario(1L);
        assertEquals(expectedGasto, result);
        verify(emMock).close();
    }

    @Test
    void testActualizarGastosConExcepcion() {
        // Simula una excepción al ejecutar la consulta de actualización
        when(emMock.createQuery(anyString())).thenThrow(new RuntimeException("Error en actualización"));

        assertDoesNotThrow(() -> gastosDAO.actualizarGastos(1L, "Comida", 100.0F));
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
        // Simula una excepción al intentar obtener gastos por categoría y usuario
        when(emMock.createQuery(anyString())).thenThrow(new RuntimeException("Error en consulta de categoría"));

        assertDoesNotThrow(() -> gastosDAO.GastosPorCategoriYusuario(1L, "Comida"));
        verify(emMock).close();
    }

//@Test
//void testObtenerUltimoGastoPorUsuarioConExcepcion() {
//    // Simula una excepción al intentar obtener el último gasto de un usuario
//    when(emMock.createQuery(anyString(), eq(Gastos.class)))
//            .thenThrow(new RuntimeException("Error en consulta de último gasto"));
//
//    assertDoesNotThrow(() -> gastosDAO.obtenerUltimoGastoPorUsuario(1L));
//    verify(emMock).close();
//}
//    @Test
//    void testObtenerLista() {
//        List<Gastos> expectedList = new ArrayList<>();
//        TypedQuery<Gastos> queryMock = mock(TypedQuery.class);
//
//        when(emMock.createQuery(anyString())).thenReturn(queryMock);
//        when(queryMock.setParameter(anyString(), anyLong())).thenReturn(queryMock);
//        when(queryMock.getResultList()).thenReturn(expectedList);
//
//        List<Gastos> result = gastosDAO.obtenerLista(45L);
//
//        assertEquals(expectedList, result);
//        verify(emMock).close();
//    }
//@Test
//void testObtenerGastosTotalesPorPeriodoConExcepcion() {
//    // Simula una excepción en la consulta de suma de gastos
//    when(emMock.createQuery(anyString(), eq(Double.class)))
//            .thenThrow(new RuntimeException("Error en la consulta de suma"));
//
//    assertDoesNotThrow(() -> gastosDAO.obtenerGastosTotalesPorPeriodo(new Date(), new Date()));
//    verify(emMock).close();
//}
//@Test
//void testListaPorPeriodoConExcepcion() {
//    // Simula una excepción al intentar obtener la lista de gastos por periodo
//    when(emMock.createQuery(anyString(), eq(Gastos.class)))
//            .thenThrow(new RuntimeException("Error en consulta por periodo"));
//
//    assertDoesNotThrow(() -> gastosDAO.listaPorPeriodo(new Date(), new Date()));
//    verify(emMock).close();
//}
//    @Test
//void testObtenerListaConExcepcion() {
//    // Simula una excepción en la creación de consulta
//    when(emMock.createQuery(anyString())).thenThrow(new RuntimeException("Error en JPQL"));
//
//    assertDoesNotThrow(() -> gastosDAO.obtenerLista(1L));
//    verify(emMock).close();
//}
}
