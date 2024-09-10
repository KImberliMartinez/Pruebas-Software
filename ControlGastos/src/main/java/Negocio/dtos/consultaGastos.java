/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.dtos;

import Persistencia.DAO.GastosDAO;
import Persistencia.DAO.IGastosDAO;
import Persistencia.entidades.Gastos;
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
        gasto= new GastosDAO();
    }

    @Override
    public void registrar(gastosDTO p) {
        Gastos nuevoGasto=new Gastos(p.getCategoria(), p.getDescripcion(), p.getGasto(), p.getFecha());
        System.out.println("registrarGastos");
        gasto.Agregar(nuevoGasto);
    }

    @Override
    public List<gastosDTO> obtenerLista() {
         List<Gastos> gas = gasto.obtenerLista(); // Obtener personas desde la base de datos
        return convertirGastosADTOs(gas);
    }

    @Override
    public List<gastosDTO> convertirGastosADTOs(List<Gastos> gastos) {
        List<gastosDTO> gastoDTO = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Gastos g : gastos) {
            gastosDTO gaDTO = new gastosDTO();
            String fechaFormateada = sdf.format(g.getFecha());
            gaDTO.setCategoria(g.getCategoria());
            gaDTO.setDescripcion(g.getDescripcion());
            gaDTO.setGasto(g.getGasto());
            gaDTO.setFecha(g.getFecha());
            

            gastoDTO.add(gaDTO);
        }
        return gastoDTO;
    }

    @Override
    public void actualizarGastos(long id,String categoria, String descripcion, float gas, Date fecha) {
      gasto.actualizarGastos(id, categoria, descripcion, gas, fecha);
    }

    @Override
    public void Eliminar(long id) {

    }
    
}
