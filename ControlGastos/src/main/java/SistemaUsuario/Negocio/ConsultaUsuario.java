/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaUsuario.Negocio;

import SistemaUsuario.Persistencia.Entidades.Usuarios;
import SistemaUsuario.Persistencia.DAO.IUsuarioDAO;
import SistemaUsuario.Persistencia.DAO.UsuarioDAO;

/**
 *
 * @author delll
 */
public class ConsultaUsuario implements IConsultaUsuario{
        IUsuarioDAO usuarios;

    public ConsultaUsuario() {
         usuarios=new UsuarioDAO();
    }
        
      @Override
    public void AgregarUsuario(UsuariosDTO usuario) {
        Usuarios usu=new Usuarios(usuario.getNombreUsuario(), usuario.getContra());
        usuarios.AgregarUsuario(usu);
    }

    @Override
    public long obtenerIDusuario(String nombre, String contra) {
        long id=usuarios.obtenerIDusuario(nombre, contra);
        if (id!=0){
            System.out.println("Usuario existente");
        }else{
             System.out.println("Usuario no encontrado");
        }
       return id;     
    }

    @Override
    public long usuarioExistente(String nombre) {
        long id=usuarios.usuarioExistente(nombre);
       if (id!=0){
            System.out.println("Usuario existente");
        }else{
          System.out.println("Usuario no encontrado"); 
        }
         return id; 
    }
}
