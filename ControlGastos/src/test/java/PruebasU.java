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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.mindrot.jbcrypt.BCrypt;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author delll
 */
public class PruebasU {
    
    public PruebasU() {
        
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
        nuevoUsuario.setUsuario("testU");
        nuevoUsuario.setContra("password123");

        // Agregar el usuario a la base de datos
        usuarioDAO.AgregarUsuario(nuevoUsuario);

        // Verificar que el ID no es 0 (el usuario fue creado y persistido en la base de datos)
        long idUsuario = usuarioDAO.obtenerIDusuario("testU", "password123");

        // El ID debe ser diferente de 0 si el usuario fue creado correctamente
        assertNotEquals(0, idUsuario, "El ID del usuario debe ser diferente de 0.");
    }

    @Test
    public void testAgregarUsuarioConContraseñaIncorrecta() {
        // Crear un usuario de prueba
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setUsuario("testUIncorrecto");
        nuevoUsuario.setContra("password123");

        // Agregar el usuario a la base de datos
        usuarioDAO.AgregarUsuario(nuevoUsuario);

        // Intentar obtener el ID con una contraseña incorrecta
        long idUsuario = usuarioDAO.obtenerIDusuario("testUIncorrecto", "noPassword");

        // El ID debe ser 0 si la contraseña es incorrecta
        assertEquals(0, idUsuario, "El ID del usuario debe ser 0 si la contraseña es incorrecta.");
    }

    
    
    @Test
    public void testAgregarGastoCorrecto() {
        // enviar id del usuario
        Usuarios usuario = new Usuarios();
        usuario.setId(15L);

        // Crear un gasto válido
        gastosDTO gastoDTO = new gastosDTO();
        gastoDTO.setCategoria("Alimentación");
        gastoDTO.setDescripcion("Compra de alimentos");
        gastoDTO.setGasto(100.50f);
        gastoDTO.setFecha(new Date());
        gastoDTO.setUsuarioId(usuario.getId()); // ID válido

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
        gastosDTO gastoDTO = new gastosDTO();
        gastoDTO.setCategoria("Transporte");
        gastoDTO.setDescripcion("Gasolina");
        gastoDTO.setGasto(50.0f);
        gastoDTO.setFecha(new Date());
        gastoDTO.setUsuarioId(999L); // ID de usuario inexistente

        // Intentar registrar el gasto
        consultaGastos consulta = new consultaGastos();
        consulta.registrar(gastoDTO);

        // Verificar que no se registró el gasto
        GastosDAO gastosDAO = new GastosDAO();
        Gastos gastoRegistrado = gastosDAO.obtenerUltimoGastoPorUsuario(999L);
        assertNull(gastoRegistrado, "No debería haberse registrado un gasto con un usuario inválido.");
    }

}
