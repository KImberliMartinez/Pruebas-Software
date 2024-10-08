/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaControlGastos.Persistencia.Entidades;

import SistemaUsuario.Persistencia.Entidades.Usuarios;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author delll
 */
@Entity
@Table(name = "gastos")
public class Gastos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Column(name = "Categoria", nullable = false, length =255)
    private String categoria;
      @Column(name = "Descripcion", nullable = false, length = 255)
    private String descripcion;
      @Column(name = "Gasto")
    private Float gasto;
      
    @Column(name = "Fecha")
    private java.util.Date fecha;
    
     @ManyToOne
    @JoinColumn(name = "usuario_id") // Nombre de la columna en la tabla de gastos
    private Usuarios usuario;

    public Gastos(String categoria, String descripcion, Float gasto, Date fecha, Usuarios usuario) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.gasto = gasto;
        this.fecha = fecha;
        this.usuario = usuario;
    }
     
     
   

    public Gastos() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Float getGasto() {
        return gasto;
    }

    public void setGasto(Float gasto) {
        this.gasto = gasto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Gastos{" + "id=" + id + ", categoria=" + categoria + ", descripcion=" + descripcion + ", gasto=" + gasto + ", fecha=" + fecha + ", usuario=" + usuario + '}';
    }
    
   

    
    
}
