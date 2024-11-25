/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaReporte.Negocio;

import SistemaControlGastos.Negocio.gastosDTO;
import SistemaControlGastos.Persistencia.DAO.GastosDAO;
import SistemaControlGastos.Persistencia.Entidades.Gastos;
import SistemaReporte.Persistencia.DAO.IReporteDAO;
import SistemaReporte.Persistencia.DAO.ReporteDAO;
import SistemaUsuario.Persistencia.DAO.IUsuarioDAO;
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
public class ConsultaReporte implements IConsultaReporte {
    IReporteDAO reporte;

    public ConsultaReporte() {
         reporte = new ReporteDAO();
    }
    
    
    @Override
 public List<gastosDTO> listaPorPeriodoSemanal(Date fecha,long idUsuario) {
    // Calcular la fecha avanzando 6 días
    Date fechaFin = new Date(fecha.getTime() + 6 * 24 * 60 * 60 * 1000);

    // Obtener los gastos entre la fecha original y la fecha avanzanda
    return convertirGastosADTOs(reporte.listaPorPeriodoYUsuario(fecha, fechaFin,idUsuario));
}
@Override
public List<gastosDTO> listaPorPeriodoMensual(Date fecha,long idUsuario) {
    return convertirGastosADTOs(reporte.listaPorPeriodoYUsuario(fecha, new Date(fecha.getTime() + (30L * 24 * 60 * 60 * 1000)), idUsuario));
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
public Map<String, Double> crearHistograma(List<gastosDTO> listaGastos) {
    Map<String, Double> histograma = new HashMap<>();
    
    // Recorrer la lista de gastos y agrupar por categoría
    for (gastosDTO gasto : listaGastos) {
        String categoria = gasto.getCategoria();
        double valor = gasto.getGasto();
        
        // Sumar el gasto a la categoría correspondiente
        histograma.put(categoria, histograma.getOrDefault(categoria, 0.0) + valor);
    }
    
    return histograma;
}
}
