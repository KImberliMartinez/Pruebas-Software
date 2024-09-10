/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.entidades.Gastos;
import java.util.Date;
import java.util.List;

/**
 *
 * @author delll
 */
public interface IGastosDAO {
    public void Agregar(Gastos gastos);
    public List<Gastos> obtenerLista();
   public void actualizarGastos(long id,String categoria,String descripcion,Float gasto);
   public void Eliminar(long id);
   public Double obtenerGastosTotalesPorPeriodo(Date inicio, Date fin);
    public List<Gastos> listaPorPeriodo(Date startDate,Date endDate);
}
