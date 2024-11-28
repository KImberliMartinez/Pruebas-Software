/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package TestNegocio;

import SistemaControlGastos.Negocio.consultaGastos;
import SistemaControlGastos.Negocio.gastosDTO;
import SistemaControlGastos.Persistencia.DAO.IGastosDAO;
import SistemaControlGastos.Persistencia.DAO.GastosDAO;
import SistemaUsuario.Persistencia.DAO.IUsuarioDAO;
import SistemaUsuario.Persistencia.DAO.UsuarioDAO;
import SistemaUsuario.Persistencia.Entidades.Usuarios;
import SistemaControlGastos.Persistencia.Entidades.Gastos;
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
public class ConsultaGastosTest {
    
    private consultaGastos consulta;
    private IGastosDAO gastosMock;
    private IUsuarioDAO usuarioMock;

    @BeforeEach
    public void setUp() {
        // Inicializamos los mocks
        gastosMock = mock(GastosDAO.class);
        usuarioMock = mock(UsuarioDAO.class);

        // Inicializamos la clase a probar, inyectando los mocks
        consulta = new consultaGastos();
        consulta.gasto = gastosMock;
        consulta.usuarios = usuarioMock;
    }

    @Test
    public void testRegistrarGastoUsuarioValido() {
        // Preparamos los datos
        gastosDTO gastoDTO = new gastosDTO();
        gastoDTO.setCategoria("Alimentación");
        gastoDTO.setDescripcion("Almuerzo");
        gastoDTO.setGasto(15.50f);
        gastoDTO.setFecha(new Date());
        gastoDTO.setUsuarioId(1L);

        // Simulamos el comportamiento de los mocks
        Usuarios usuario = new Usuarios("testUser", "testPassword");
        when(usuarioMock.obtenerSoloUusario(1L)).thenReturn(usuario);
        
        // Llamamos al método
        consulta.registrar(gastoDTO);

        // Verificamos que se haya llamado al método de agregar en GastosDAO
        verify(gastosMock, times(1)).Agregar(any(Gastos.class));
    }

    @Test
    public void testRegistrarGastoUsuarioNoValido() {
        // Preparamos los datos
        gastosDTO gastoDTO = new gastosDTO();
        gastoDTO.setCategoria("Transporte");
        gastoDTO.setDescripcion("Taxi");
        gastoDTO.setGasto(25.75f);
        gastoDTO.setFecha(new Date());
        gastoDTO.setUsuarioId(1L);

        // Simulamos que el usuario no existe
        when(usuarioMock.obtenerSoloUusario(1L)).thenReturn(null);

        // Llamamos al método
        consulta.registrar(gastoDTO);

        // Verificamos que no se haya llamado al método de agregar en GastosDAO
        verify(gastosMock, times(0)).Agregar(any(Gastos.class));
    }

//    @Test
//    public void testObtenerListaGastos() {
//        // Preparamos los datos
//        List<Gastos> gastosList = new ArrayList<>();
//        Gastos gasto1 = new Gastos("Alimentación", "Desayuno", 5.0f, new Date(), new Usuarios());
//        Gastos gasto2 = new Gastos("Transporte", "Bus", 2.0f, new Date(), new Usuarios());
//        gastosList.add(gasto1);
//        gastosList.add(gasto2);
//
//        // Simulamos el comportamiento de los mocks
//        when(gastosMock.obtenerLista(1L)).thenReturn(gastosList);
//
//        // Llamamos al método
//        List<gastosDTO> result = consulta.obtenerLista(1L);
//
//        // Verificamos que el tamaño de la lista es correcto
//        assertEquals(2, result.size());
//    }

    @Test
    public void testActualizarGastos() {
        // Llamamos al método
        consulta.actualizarGastos(1L, "Alimentación", 10.0f);

        // Verificamos que el método actualizarGastos en GastosDAO haya sido llamado
        verify(gastosMock, times(1)).actualizarGastos(1L, "Alimentación", 10.0f);
    }

    @Test
    public void testEliminarGasto() {
        // Llamamos al método
        consulta.Eliminar(1L);

        // Verificamos que el método Eliminar en GastosDAO haya sido llamado
        verify(gastosMock, times(1)).Eliminar(1L);
    }

    @Test
    public void testObtenerGastosTotalesPorPeriodo() {
        // Preparamos las fechas
        Date startDate = new Date();
        Date endDate = new Date();

        // Simulamos el comportamiento de los mocks
        when(gastosMock.obtenerGastosTotalesPorPeriodo(startDate, endDate)).thenReturn(100.0);

        // Llamamos al método
        Double total = consulta.obtenerGastosTotalesPorPeriodo(startDate, endDate);

        // Verificamos que el total sea correcto
        assertEquals(100.0, total);
    }



    @Test
    public void testGastosPorUsuario() {
        // Simulamos el comportamiento de los mocks
        when(gastosMock.GastosPorUusario(1L)).thenReturn(200.0);

        // Llamamos al método
        Double total = consulta.GastosPorUusario(1L);

        // Verificamos que el total sea correcto
        assertEquals(200.0, total);
    }

    @Test
    public void testGastosPorCategoriaYUsuario() {
        // Simulamos el comportamiento de los mocks
        when(gastosMock.GastosPorCategoriYusuario(1L, "Alimentación")).thenReturn(50.0);

        // Llamamos al método
        Double total = consulta.GastosPorCategoriYusuario(1L, "Alimentación");

        // Verificamos que el total sea correcto
        assertEquals(50.0, total);
    }
    
}
