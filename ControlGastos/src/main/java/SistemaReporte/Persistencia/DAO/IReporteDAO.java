/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SistemaReporte.Persistencia.DAO;

import SistemaControlGastos.Persistencia.Entidades.Gastos;
import java.util.Date;
import java.util.List;

/**
 *
 * @author delll
 */
public interface IReporteDAO {
     public List<Gastos> listaPorPeriodo(Date startDate,Date endDate);
    public List<Gastos> listaPorPeriodoYUsuario(Date startDate, Date endDate, long usuarioId);
    
}
