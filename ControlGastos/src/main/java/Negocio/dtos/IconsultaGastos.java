/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio.dtos;

import Persistencia.entidades.Gastos;
import java.util.Date;
import java.util.List;

/**
 *
 * @author delll
 */
public interface IconsultaGastos {
     public void registrar(gastosDTO p);
    //public Gastos BuscaPersonaPorRFC(String rfc);
    public List<gastosDTO> obtenerLista();
    public List<gastosDTO> convertirGastosADTOs(List<Gastos> gastos);
    public void actualizarGastos(long id,String categoria,String descripcion,float gas,Date fecha);
   public void Eliminar(java.lang.Long id);
}
