/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.entidades.Gastos;
import Persistencia.entidades.Usuarios;
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
    public void AgregarUsuario(Usuarios usuario);
    public long obtenerIDusuario(String nombre,String contra);
    public Usuarios obtenerSoloUusario(long id);
    public long usuarioExistente(String nombre);

    
}
