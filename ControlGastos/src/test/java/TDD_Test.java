import SistemaControlGastos.Negocio.gastosDTO;
import SistemaControlGastos.Persistencia.Entidades.Gastos;
import SistemaReporte.Negocio.ConsultaReporte;
import SistemaReporte.Persistencia.DAO.ReporteDAO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
/**
 *
 * @author Arell
 */
public class TDD_Test {

 @InjectMocks
    private ConsultaReporte consultaReporte;

    @Mock
    private ReporteDAO reporteDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    } 
    
    @Test
    public void testListaPorPeriodoAnual() {
        // Arrange
        Date fecha = new Date();
        long idUsuario = 1L;
        List<Gastos> expectedGastos = Arrays.asList(new Gastos(), new Gastos());
        List<gastosDTO> expectedDTOs = Arrays.asList(new gastosDTO(), new gastosDTO());

        when(reporteDAO.listaPorPeriodoYUsuario(any(Date.class), any(Date.class), eq(idUsuario)))
                .thenReturn(expectedGastos);
        when(consultaReporte.convertirGastosADTOs(expectedGastos)).thenReturn(expectedDTOs);

        // Act
        List<gastosDTO> actualDTOs = consultaReporte.listaPorPeriodoAnual(fecha, idUsuario);

        // Assert
        assertEquals(expectedDTOs, actualDTOs);
    }

    @Test
    public void testListaPorPeriodoAnualFechaInvalida() {
        // Arrange
        Date fecha = new Date();
        Date fechaInvalida = new Date(fecha.getTime() + ((365L + 7) * 24 * 60 * 60 * 1000));
        long idUsuario = 1L;
        List<Gastos> expectedGastos = Collections.emptyList();
        List<gastosDTO> expectedDTOs = Collections.emptyList();

        when(reporteDAO.listaPorPeriodoYUsuario(any(Date.class), eq(fechaInvalida), eq(idUsuario)))
                .thenReturn(expectedGastos);
        when(consultaReporte.convertirGastosADTOs(expectedGastos)).thenReturn(expectedDTOs);

        // Act
        List<gastosDTO> actualDTOs = consultaReporte.listaPorPeriodoAnual(fecha, idUsuario);

        // Assert
        assertEquals(expectedDTOs, actualDTOs, "No debería haberse registrado ningún gasto con una fecha fuera del lapso de un año.");
    }
}
