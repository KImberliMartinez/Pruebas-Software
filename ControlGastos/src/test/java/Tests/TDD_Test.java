package Tests;

import SistemaControlGastos.Negocio.gastosDTO;
import SistemaControlGastos.Persistencia.Entidades.Gastos;
import SistemaReporte.Negocio.ConsultaReporte;
import SistemaReporte.Persistencia.DAO.ReporteDAO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
/**
 *
 * @author Arell
 */
@ExtendWith(MockitoExtension.class)
public class TDD_Test {

    @Mock
    private ReporteDAO reporteDAO; // Mock del DAO

    @InjectMocks
    private ConsultaReporte ConsultaReporte; // Clase bajo prueba

@Test
void testListaPorPeriodoAnualConMocks() {
    // Crear mock de Gastos
    Gastos gasto1 = mock(Gastos.class);
    Gastos gasto2 = mock(Gastos.class);

    when(gasto1.getId()).thenReturn(1L);
    when(gasto2.getId()).thenReturn(2L);

    List<Gastos> gastosMock = List.of(gasto1, gasto2);
    when(reporteDAO.listaPorPeriodoYUsuario(any(Date.class), any(Date.class), eq(1L)))
            .thenReturn(gastosMock);

    // Fecha de inicio ficticia
    Date fechaInicio = new Date();

    // Llamar al método bajo prueba
    List<gastosDTO> resultado = ConsultaReporte.listaPorPeriodoAnual(fechaInicio, 1L);

    // Verificar resultados
    assertNotNull(resultado);
    assertEquals(2, resultado.size());
    verify(reporteDAO).listaPorPeriodoYUsuario(eq(fechaInicio), any(Date.class), eq(1L));
}



    @Test
    void testListaPorPeriodoAnualConExcepcion() {
        // Simula una excepción en el DAO
        when(reporteDAO.listaPorPeriodoYUsuario(any(Date.class), any(Date.class), anyLong()))
                .thenThrow(new RuntimeException("Error de base de datos"));

        // Verificar que el método lanza la excepción esperada
        Date fechaInicio = new Date();
        assertThrows(RuntimeException.class, () -> ConsultaReporte.listaPorPeriodoAnual(fechaInicio, 1L));

        // Verificar que el DAO fue llamado
        verify(reporteDAO).listaPorPeriodoYUsuario(any(Date.class), any(Date.class), anyLong());
    }
}
