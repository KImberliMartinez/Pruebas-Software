/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package TestNegocio;

import SistemaReporte.Negocio.ConsultaReporte;
import SistemaControlGastos.Persistencia.Entidades.Gastos;
import SistemaControlGastos.Negocio.gastosDTO;
import SistemaReporte.Persistencia.DAO.IReporteDAO;
import SistemaReporte.Persistencia.DAO.ReporteDAO;
import SistemaUsuario.Persistencia.Entidades.Usuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carlos
 */
public class ConsultaReporteTest {
    
    private ConsultaReporte consultaReporte;
    private IReporteDAO reporteMock;

    @BeforeEach
    public void setUp() {
        // Inicializamos el mock de IReporteDAO
        reporteMock = mock(ReporteDAO.class);

        // Inicializamos la clase a probar, inyectando el mock
        consultaReporte = new ConsultaReporte();
        consultaReporte.reporte = reporteMock;
    }

    
    
    
    
    @Test
    public void testListaPorPeriodoSemanal() {
        // Fecha de inicio de la semana (lunes)
    Date fechaInicio = new Date();
    
    // Creamos una lista de Gastos con valores completos
    List<Gastos> mockGastos = new ArrayList<>();
    Gastos gasto = new Gastos();
    gasto.setId(1L); // No olvides asignar un ID no nulo
    gasto.setCategoria("Alimentación");
    gasto.setDescripcion("Comida rápida");
    gasto.setGasto(15.0f);
    gasto.setFecha(fechaInicio);
    
    mockGastos.add(gasto);
    
    // Configuramos el mock para que devuelva la lista de gastos
    when(reporteMock.listaPorPeriodoYUsuario(eq(fechaInicio), eq(new Date(fechaInicio.getTime() + 6 * 24 * 60 * 60 * 1000)), eq(1L)))
        .thenReturn(mockGastos);
    
    // Llamamos al método
    List<gastosDTO> resultado = consultaReporte.listaPorPeriodoSemanal(fechaInicio, 1L);
    
    // Verificamos que la lista de resultados no sea vacía
    assertNotNull(resultado);
    assertFalse(resultado.isEmpty());
    assertEquals(1, resultado.size());
    
    // Verificamos que el mock fue invocado correctamente
    verify(reporteMock).listaPorPeriodoYUsuario(eq(fechaInicio), eq(new Date(fechaInicio.getTime() + 6 * 24 * 60 * 60 * 1000)), eq(1L));
}

    @Test
    public void testListaPorPeriodoMensual() {
        // Creamos una fecha de inicio (por ejemplo, 1 de enero de 2024)
    Date fechaInicio = new Date(2024, 0, 1); // 1 de enero de 2024

    // Creamos una fecha final sumando 30 días
    Date fechaFin = new Date(fechaInicio.getTime() + (30L * 24 * 60 * 60 * 1000)); // 30 días después

    // Creamos una lista de Gastos con datos válidos
    List<Gastos> mockGastos = new ArrayList<>();
    Gastos gasto = new Gastos();
    gasto.setId(1L);  // Asegúrate de que el id no sea null
    gasto.setCategoria("Alimentación");
    gasto.setDescripcion("Supermercado");
    gasto.setGasto(200.0f);
    gasto.setFecha(fechaInicio);

    mockGastos.add(gasto);

    // Configuramos el mock para que devuelva la lista de gastos
    when(reporteMock.listaPorPeriodoYUsuario(eq(fechaInicio), eq(fechaFin), eq(1L)))
        .thenReturn(mockGastos);

    // Llamamos al método
    List<gastosDTO> resultado = consultaReporte.listaPorPeriodoMensual(fechaInicio, 1L);

    // Verificamos que la lista no esté vacía
    assertNotNull(resultado);
    assertFalse(resultado.isEmpty());
    assertEquals(1, resultado.size());

    // Verificamos que el DTO convertido tenga los valores correctos
    assertEquals("Alimentación", resultado.get(0).getCategoria());
    assertEquals(200.0f, resultado.get(0).getGasto(), 0.0f);

    // Verificamos que el mock haya sido llamado
    verify(reporteMock).listaPorPeriodoYUsuario(eq(fechaInicio), eq(fechaFin), eq(1L));
}


    
}
