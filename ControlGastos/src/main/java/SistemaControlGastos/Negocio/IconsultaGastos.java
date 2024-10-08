/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SistemaControlGastos.Negocio;

import SistemaUsuario.Negocio.UsuariosDTO;
import SistemaControlGastos.Persistencia.Entidades.Gastos;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author delll
 */
public interface IconsultaGastos {
     public void registrar(gastosDTO p);
    //public Gastos BuscaPersonaPorRFC(String rfc);
    public List<gastosDTO> obtenerLista(long usuarioId);
    public List<gastosDTO> convertirGastosADTOs(List<Gastos> gastos);
    public void actualizarGastos(long id,String categoria,float gas);
   public void Eliminar(long id);
   public Double obtenerGastosTotalesPorPeriodo(Date inicio, Date fin);
   public List<gastosDTO> listaPorPeriodo(Date startDate, Date endDate);
     public Double GastosPorUusario(long id);
    public Double GastosPorCategoriYusuario(long id,String categoria);
     

}
