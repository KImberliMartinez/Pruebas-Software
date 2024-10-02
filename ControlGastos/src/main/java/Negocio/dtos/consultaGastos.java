/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.dtos;

import Persistencia.DAO.GastosDAO;
import Persistencia.DAO.IGastosDAO;
import Persistencia.entidades.Gastos;
import Persistencia.entidades.Usuarios;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author delll
 */
public class consultaGastos implements IconsultaGastos {

    IGastosDAO gasto;
    private boolean registroEnviado = false;

    public consultaGastos() {
        gasto = new GastosDAO();
    }

    @Override
    public void registrar(gastosDTO p) {
        Usuarios usuario= gasto.obtenerSoloUusario(p.getUsuarioId());
        System.out.println(usuario);
        if(usuario!=null){
        Gastos nuevoGasto = new Gastos(p.getCategoria(),p.getDescripcion(),p.getGasto(), p.getFecha(), usuario);
        System.out.println(usuario);
        System.out.println("registrarGastos");
        gasto.Agregar(nuevoGasto);
        }else{
            System.out.println("No fue posible realizar esta accion");
        }
    }

    @Override
    public List<gastosDTO> obtenerLista(long usuarioId) {
        List<Gastos> gas = gasto.obtenerLista(usuarioId); // Obtener personas desde la base de datos
        return convertirGastosADTOs(gas);
    }

    @Override
    public List<gastosDTO> convertirGastosADTOs(List<Gastos> gastos) {
        List<gastosDTO> gastoDTO = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Gastos g : gastos) {
            gastosDTO gaDTO = new gastosDTO();
            gaDTO.setId(g.getId());
            gaDTO.setCategoria(g.getCategoria());
            gaDTO.setDescripcion(g.getDescripcion());
            gaDTO.setGasto(g.getGasto());
            gaDTO.setFecha(g.getFecha());

            gastoDTO.add(gaDTO);
        }
        return gastoDTO;
    }

    @Override
    public void actualizarGastos(long id, String categoria, float gas) {
        gasto.actualizarGastos(id, categoria, gas);
    }

    @Override
    public void Eliminar(long id) {
        gasto.Eliminar(id);
    }

    @Override
    public Double obtenerGastosTotalesPorPeriodo(Date inicio, Date fin) {
        return gasto.obtenerGastosTotalesPorPeriodo(inicio, fin);
    }

    @Override
    public List<gastosDTO> listaPorPeriodo(Date startDate, Date endDate) {
        List<Gastos> gastos = gasto.listaPorPeriodo(startDate, endDate);
        System.out.println("Número de registros obtenidos: " + gastos.size());
        return convertirGastosADTOs(gastos);
    }

    @Override
    public void AgregarUsuario(UsuariosDTO usuario) {
        Usuarios usu=new Usuarios(usuario.getNombreUsuario(), usuario.getContra());
        gasto.AgregarUsuario(usu);
    }

    @Override
    public long obtenerIDusuario(String nombre, String contra) {
        long id=gasto.obtenerIDusuario(nombre, contra);
        if (id!=0){
            System.out.println("Usuario existente");
        }else{
             System.out.println("Usuario no encontrado");
        }
       return id;     
    }

    @Override
    public long usuarioExistente(String nombre) {
        long id=gasto.usuarioExistente(nombre);
       if (id!=0){
            System.out.println("Usuario existente");
        }else{
          System.out.println("Usuario no encontrado"); 
        }
         return id; 
    }

}
