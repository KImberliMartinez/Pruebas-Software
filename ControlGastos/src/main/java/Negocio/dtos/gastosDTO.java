/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.dtos;

import java.util.Date;

/**
 *
 * @author delll
 */
public class gastosDTO {
    long id;
    String categoria;
    String descripcion;
    float gasto;
    Date fecha;

    public gastosDTO() {
    }

    
    public gastosDTO(String categoria, String descripcion, float gasto, Date fecha) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.gasto = gasto;
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getGasto() {
        return gasto;
    }

    public void setGasto(float gasto) {
        this.gasto = gasto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "gastosDTO{" + "categoria=" + categoria + ", descripcion=" + descripcion + ", gasto=" + gasto + ", fecha=" + fecha + '}';
    }
     
}
