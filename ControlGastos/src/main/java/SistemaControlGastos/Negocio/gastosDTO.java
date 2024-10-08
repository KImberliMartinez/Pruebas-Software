/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaControlGastos.Negocio;

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
    long usuarioId;

    public gastosDTO() {
    }

    public gastosDTO(String categoria, String descripcion, float gasto, Date fecha, long usuarioId) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.gasto = gasto;
        this.fecha = fecha;
        this.usuarioId = usuarioId;
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

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "gastosDTO{" + "id=" + id + ", categoria=" + categoria + ", descripcion=" + descripcion + ", gasto=" + gasto + ", fecha=" + fecha + ", usuarioId=" + usuarioId + '}';
    }

         
}
