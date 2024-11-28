package TestPersistencia;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */


import org.junit.jupiter.api.Test;
import SistemaUsuario.Persistencia.DAO.UsuarioDAO;
import SistemaUsuario.Persistencia.Entidades.Usuarios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 *
 * @author Carlos
 */
public class UsuarioDAOTest {
    

    
    @Test
    public void testAgregarUsuario() {
        // Crear un mock de EntityManager y EntityTransaction
        EntityManager emMock = mock(EntityManager.class);
        EntityTransaction transactionMock = mock(EntityTransaction.class);

        // Simular que emMock tiene un transaction activo
        when(emMock.getTransaction()).thenReturn(transactionMock);

        // Crear un UsuarioDAO con EntityManager mockeado
        UsuarioDAO dao = new UsuarioDAO();
        dao.emf = mock(EntityManagerFactory.class);
        when(dao.emf.createEntityManager()).thenReturn(emMock);

        // Crear un usuario de prueba
        Usuarios usuario = new Usuarios();
        usuario.setUsuario("prueba");
        usuario.setContra("contraseña123");

        // Llamar al método AgregarUsuario
        dao.AgregarUsuario(usuario);

        // Verificar que se haya hecho el commit de la transacción
        verify(transactionMock).commit();
    }



@Test
public void testObtenerIDusuario() {
    UsuarioDAO dao = new UsuarioDAO();
    String nombre = "prueba";
    String contra = "contraseña123";

    // Crear el usuario para asegurarse de que exista
    Usuarios usuario = new Usuarios();
    usuario.setUsuario(nombre);
    usuario.setContra(contra);
    dao.AgregarUsuario(usuario);

    // Ahora obtén el ID del usuario
    long id = dao.obtenerIDusuario(nombre, contra);

    assertTrue(id > 0); // Validar que se devuelve un ID válido

    // Validar que el ID corresponde al usuario creado
    Usuarios usuarioRecuperado = dao.obtenerSoloUusario(id);
    assertNotNull(usuarioRecuperado);
    assertEquals(nombre, usuarioRecuperado.getUsuario());
}


    @Test
public void testUsuarioExistente() {
    UsuarioDAO dao = new UsuarioDAO();
    String nombre = "prueba";

    // Crear el usuario para asegurarse de que exista
    Usuarios usuario = new Usuarios();
    usuario.setUsuario(nombre);
    usuario.setContra("contraseña123");
    dao.AgregarUsuario(usuario);

    // Verificar si el usuario existe
    long id = dao.usuarioExistente(nombre);

    assertTrue(id > 0); // Validar que se devuelve un ID si el usuario existe

    // Puedes validar que el ID corresponda al usuario creado
    Usuarios usuarioRecuperado = dao.obtenerSoloUusario(id);
    assertNotNull(usuarioRecuperado);
    assertEquals(nombre, usuarioRecuperado.getUsuario());
}

@Test
    public void testUsuarioExistente_NoResultException() {
        // Crear un mock de EntityManager y TypedQuery
        EntityManager emMock = mock(EntityManager.class);
        TypedQuery<Long> queryMock = mock(TypedQuery.class);

        // Simular el comportamiento de la consulta: cuando se hace la consulta, se lanza NoResultException
        when(emMock.createQuery(anyString(), eq(Long.class))).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), anyString())).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenThrow(new NoResultException("Usuario no encontrado"));

        // Crear un UsuarioDAO con el EntityManager mockeado
        UsuarioDAO dao = new UsuarioDAO();
        dao.emf = mock(EntityManagerFactory.class);
        when(dao.emf.createEntityManager()).thenReturn(emMock);

        // Llamar al método usuarioExistente con un nombre que no existe
        long id = dao.usuarioExistente("usuario_inexistente");

        // Verificar que el ID devuelto es 0, que es el comportamiento cuando no se encuentra el usuario
        assertEquals(0, id);
    }





    
}



