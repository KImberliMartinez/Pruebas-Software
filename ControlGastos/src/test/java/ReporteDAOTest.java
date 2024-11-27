/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import SistemaReporte.Persistencia.DAO.ReporteDAO;
import SistemaControlGastos.Persistencia.Entidades.Gastos;
import org.junit.jupiter.api.*;
import org.mockito.*;

import javax.persistence.*;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carlos
 */
public class ReporteDAOTest {
    
    private ReporteDAO reporteDAO;

    @Mock
    private EntityManagerFactory emfMock;

    @Mock
    private EntityManager emMock;

    @Mock
    private TypedQuery<Gastos> typedQueryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reporteDAO = new ReporteDAO();
        reporteDAO.emf = emfMock; // Sustituye la fábrica real por el mock
        when(emfMock.createEntityManager()).thenReturn(emMock);
    }
    
    
    @Test
void testListaPorPeriodo() {
    // Mock de resultados
    List<Gastos> gastosMock = List.of(new Gastos(), new Gastos());
    when(emMock.createQuery(anyString(), eq(Gastos.class))).thenReturn(typedQueryMock);
    when(typedQueryMock.setParameter(eq("startDate"), any())).thenReturn(typedQueryMock);
    when(typedQueryMock.setParameter(eq("endDate"), any())).thenReturn(typedQueryMock);
    when(typedQueryMock.getResultList()).thenReturn(gastosMock);

    // Ejecutar el método
    Date startDate = new Date();
    Date endDate = new Date();
    List<Gastos> resultado = reporteDAO.listaPorPeriodo(startDate, endDate);

    // Verificar resultados
    assertNotNull(resultado);
    assertEquals(2, resultado.size());
    verify(emMock).close();
}

@Test
void testListaPorPeriodoConExcepcion() {
    // Simula una excepción al crear la consulta
    when(emMock.createQuery(anyString(), eq(Gastos.class))).thenThrow(new RuntimeException("Error en consulta"));

    // Ejecutar el método y verificar que no lanza excepción
    Date inicioDate = new Date();
    Date finalDate = new Date();
    assertThrows(RuntimeException.class, () -> reporteDAO.listaPorPeriodo(inicioDate, finalDate));

    // Verificar que el EntityManager se cierra
    verify(emMock).close();
}

@Test
void testListaPorPeriodoYUsuario() {
    // Mock de la transacción
    EntityTransaction transactionMock = mock(EntityTransaction.class);

    // Mock del EntityManager
    when(emMock.getTransaction()).thenReturn(transactionMock);
    
    // Asegúrate de que la transacción esté activa
    when(transactionMock.isActive()).thenReturn(true);

    // Mock de los resultados
    List<Gastos> gastosMock = List.of(new Gastos(), new Gastos());
    TypedQuery<Gastos> typedQueryMock = mock(TypedQuery.class);
    when(emMock.createQuery(anyString(), eq(Gastos.class))).thenReturn(typedQueryMock);
    when(typedQueryMock.setParameter(eq("startDate"), any())).thenReturn(typedQueryMock);
    when(typedQueryMock.setParameter(eq("endDate"), any())).thenReturn(typedQueryMock);
    when(typedQueryMock.setParameter(eq("usuarioId"), anyLong())).thenReturn(typedQueryMock);
    when(typedQueryMock.getResultList()).thenReturn(gastosMock);

    // Ejecutar el método
    Date inicioDate = new Date();
    Date finalDate = new Date();
    long usuarioId = 1L;
    List<Gastos> resultado = reporteDAO.listaPorPeriodoYUsuario(inicioDate, finalDate, usuarioId);

    // Verificar resultados
    assertNotNull(resultado);
    assertEquals(2, resultado.size());  // Verificamos que la lista contiene 2 elementos
    verify(emMock).close();  // Verificamos que se cierre el EntityManager
}


@Test
void testListaPorPeriodoYUsuarioConExcepcion() {
    // Mock de la transacción
    EntityTransaction transactionMock = mock(EntityTransaction.class);

    // Mock del EntityManager
    when(emMock.getTransaction()).thenReturn(transactionMock);
    
    // Asegúrate de que la transacción esté activa
    when(transactionMock.isActive()).thenReturn(true);

    // Mock de la consulta
    TypedQuery<Gastos> typedQueryMock = mock(TypedQuery.class);
    when(emMock.createQuery(anyString(), eq(Gastos.class))).thenReturn(typedQueryMock);
    
    // Simulamos que se lanza una excepción en la ejecución de la consulta
    when(typedQueryMock.setParameter(eq("startDate"), any())).thenReturn(typedQueryMock);
    when(typedQueryMock.setParameter(eq("endDate"), any())).thenReturn(typedQueryMock);
    when(typedQueryMock.setParameter(eq("usuarioId"), anyLong())).thenReturn(typedQueryMock);
    when(typedQueryMock.getResultList()).thenThrow(new RuntimeException("Error en la consulta"));

    // Ejecutar el método
    Date startDate = new Date();
    Date endDate = new Date();
    long usuarioId = 1L;
    
    // La excepción es lanzada aquí
    List<Gastos> result = reporteDAO.listaPorPeriodoYUsuario(startDate, endDate, usuarioId);

    // Verificar que el resultado es null debido a la excepción
    assertNull(result);

    // Verificar que se haya llamado a rollback
    verify(transactionMock).rollback();

    // Verificar que se cierra el EntityManager
    verify(emMock).close();
}



    
}
