/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SistemaControlGastos.Persistencia.DAO;

import SistemaControlGastos.Persistencia.Entidades.Gastos;
import SistemaUsuario.Persistencia.Entidades.Usuarios;
import java.util.Date;
import java.util.List;

/**
 *
 * @author delll
 */
public interface IGastosDAO {
    public void Agregar(Gastos gastos);
    public List<Gastos> obtenerLista(long usuarioId);
   public void actualizarGastos(long id,String categoria,Float gasto);
   public void Eliminar(long id);
   public Double obtenerGastosTotalesPorPeriodo(Date inicio, Date fin);
    public List<Gastos> listaPorPeriodo(Date startDate,Date endDate);
    public Double GastosPorUusario(long id);
    public Double GastosPorCategoriYusuario(long id,String categoria);
    
    
}
