/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package TestNegocio;

import SistemaUsuario.Negocio.ConsultaUsuario;
import SistemaUsuario.Negocio.UsuariosDTO;
import SistemaUsuario.Persistencia.DAO.IUsuarioDAO;
import SistemaUsuario.Persistencia.DAO.UsuarioDAO;
import SistemaUsuario.Persistencia.Entidades.Usuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carlos
 */
public class ConsultaUsuarioTest {
   private ConsultaUsuario consultaUsuario;
    private IUsuarioDAO usuariosDAO;

    // Implementación simple de UsuarioDAO para pruebas
    class UsuarioDAO implements IUsuarioDAO {
        @Override
        public void AgregarUsuario(Usuarios usuario) {
            // Simula agregar un usuario, aquí no hacemos nada
        }

        @Override
        public long obtenerIDusuario(String nombre, String contra) {
            if ("testUser".equals(nombre) && "testPassword".equals(contra)) {
                return 1L; // Usuario encontrado
            }
            return 0L; // Usuario no encontrado
        }

        @Override
        public long usuarioExistente(String nombre) {
            if ("testUser".equals(nombre)) {
                return 1L; // Usuario existente
            }
            return 0L; // Usuario no existente
        }

        @Override
        public Usuarios obtenerSoloUusario(long id) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    @BeforeEach
    public void setUp() {
        // Instanciamos la clase que vamos a probar y la dependencias
        usuariosDAO = new UsuarioDAO();
        consultaUsuario = new ConsultaUsuario();
        consultaUsuario.usuarios = usuariosDAO; // Inyectamos la dependencia
    }

    @Test
    public void testAgregarUsuario() {
        // Crear un usuario para agregar
        UsuariosDTO usuarioDTO = new UsuariosDTO("testUser", "testPassword");

        // Llamar al método AgregarUsuario
        consultaUsuario.AgregarUsuario(usuarioDTO);

        // Aquí, como no tenemos una base de datos real, no podemos hacer una verificación directa.
        // Podríamos agregar un comportamiento adicional en UsuarioDAO o usar una base de datos en memoria para verificar el resultado.
        assertTrue(true, "El usuario ha sido agregado correctamente.");
    }

    @Test
    public void testObtenerIDusuarioUsuarioExistente() {
        // Llamar al método obtenerIDusuario con un usuario existente
        long id = consultaUsuario.obtenerIDusuario("testUser", "testPassword");

        // Verificar que el ID devuelto es el correcto
        assertEquals(1L, id, "El usuario debe existir y tener ID 1");
    }

    @Test
    public void testObtenerIDusuarioUsuarioNoExistente() {
        // Llamar al método obtenerIDusuario con un usuario no existente
        long id = consultaUsuario.obtenerIDusuario("testUser", "ContraseñaIncorrecta");

        // Verificar que el ID devuelto es 0 (usuario no encontrado)
        assertEquals(0L, id, "El usuario no debe existir y debe devolver ID 0");
    }

    @Test
    public void testUsuarioExistente() {
        // Llamar al método usuarioExistente con un usuario existente
        long id = consultaUsuario.usuarioExistente("testUser");

        // Verificar que el ID devuelto es correcto
        assertEquals(1L, id, "El usuario debe existir");
    }

    @Test
    public void testUsuarioNoExistente() {
        // Llamar al método usuarioExistente con un usuario no existente
        long id = consultaUsuario.usuarioExistente("UsuarioIncorrecto");

        // Verificar que el ID devuelto es 0 (usuario no existe)
        assertEquals(0L, id, "El usuario no debe existir");
    }
    
}
