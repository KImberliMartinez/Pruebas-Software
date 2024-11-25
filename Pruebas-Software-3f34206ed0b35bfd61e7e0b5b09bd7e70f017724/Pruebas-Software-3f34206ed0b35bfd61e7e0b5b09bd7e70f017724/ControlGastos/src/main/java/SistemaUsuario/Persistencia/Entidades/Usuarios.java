/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaUsuario.Persistencia.Entidades;

import SistemaControlGastos.Persistencia.Entidades.Gastos;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author delll
 */
@Entity
@Table(name = "Usuarios")
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "usuario", nullable = false, length =255,unique = true)
    private String usuario;
    
      @Column(name = "contra", nullable = false, length = 255)
    private String contra;

       @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Gastos> gastos;

    public Usuarios() {
    }
       
    public Usuarios(Long id, String usuario, String contra) {
        this.id = id;
        this.usuario = usuario;
        this.contra = contra;
    }

    public Usuarios(String usuario, String contra) {
        this.usuario = usuario;
        this.contra = contra;
    }
      
      
      
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public Set<Gastos> getGastos() {
        return gastos;
    }

    public void setGastos(Set<Gastos> gastos) {
        this.gastos = gastos;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "id=" + id + ", usuario=" + usuario + ", contra=" + contra + ", gastos=" + gastos + '}';
    }

   

   
   
    
}
