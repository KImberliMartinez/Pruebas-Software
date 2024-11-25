/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SistemaReporte.Negocio;

import SistemaControlGastos.Negocio.gastosDTO;
import SistemaControlGastos.Persistencia.Entidades.Gastos;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author delll
 */
public interface IConsultaReporte {
     public List<gastosDTO> listaPorPeriodoMensual(Date fecha,long idUsuario);
    public List<gastosDTO> listaPorPeriodoSemanal(Date fecha,long idUsuario);
public Map<String, Double> crearHistograma(List<gastosDTO> listaGastos);
    public List<gastosDTO> convertirGastosADTOs(List<Gastos> gastos);

}
