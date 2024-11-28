/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaControlGastos.Negocio;

import SistemaUsuario.Negocio.UsuariosDTO;
import SistemaControlGastos.Negocio.IconsultaGastos;
import SistemaControlGastos.Persistencia.DAO.GastosDAO;
import SistemaControlGastos.Persistencia.DAO.IGastosDAO;
import SistemaControlGastos.Persistencia.Entidades.Gastos;
import SistemaUsuario.Persistencia.Entidades.Usuarios;
import SistemaUsuario.Persistencia.DAO.IUsuarioDAO;
import SistemaUsuario.Persistencia.DAO.UsuarioDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author delll
 */
public class consultaGastos implements IconsultaGastos {

    public IGastosDAO gasto;
    public IUsuarioDAO usuarios;
//    private boolean registroEnviado = false;

    public consultaGastos() {
        gasto = new GastosDAO();
        usuarios=new UsuarioDAO();
    }

    @Override
    public void registrar(gastosDTO p) {
        Usuarios usuario= usuarios.obtenerSoloUusario(p.getUsuarioId());
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
    public Double GastosPorUusario(long id) {
        return gasto.GastosPorUusario(id);
    }

    @Override
    public Double GastosPorCategoriYusuario(long id, String categoria) {
        return gasto.GastosPorCategoriYusuario(id, categoria);
    }
}
