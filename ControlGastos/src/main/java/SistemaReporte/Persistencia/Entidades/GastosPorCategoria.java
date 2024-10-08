/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaReporte.Persistencia.Entidades;

/**
 *
 * @author Arell
 */
public class GastosPorCategoria {
    
    private String categoria;
    private Double gastoTotal;

    public GastosPorCategoria(String categoria, Double gastoTotal) {
        this.categoria = categoria;
        this.gastoTotal = gastoTotal;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getGastoTotal() {
        return gastoTotal;
    }

    public void setGastoTotal(Double gastoTotal) {
        this.gastoTotal = gastoTotal;
    }
    
    
}
