package entidades;

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
@Table(name = "placas")
public class Placa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NumPlacas",unique=true)
    private String numeroPlacas;
    

    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;

    @Column(name = "fecha_recepcion")
    @Temporal(TemporalType.DATE)
    private Date fechaRecepcion;

    @Column(name = "costo")
    private Float costo;

      
    @ManyToOne
    @JoinColumn(name = "id_automovil")
    private Automovil automovil;

    @Column(name = "Activa")
    private boolean activa;
    
    public Placa() {
    }

    public Placa(String numeroPlacas, Date fechaEmision, Date fechaRecepcion, Float costo, Automovil automovil,boolean activa) {
        this.numeroPlacas = numeroPlacas;
        this.fechaEmision = fechaEmision;
        this.fechaRecepcion = fechaRecepcion;
        this.costo = costo;
        this.automovil = automovil;
        this.activa=activa;
    }


    

    public void calcularCosto() {
        if (this.numeroPlacas == null) {
            // Si no hay número de placas anteriores, es un automóvil nuevo
            this.costo = 1500f;
        } else {
            // Si hay número de placas anteriores, es un automóvil usado
            this.costo = 1000f;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroPlacasAnt() {
        return numeroPlacas;
    }

    public void setNumeroPlacasAnt(String numeroPlacasAnt) {
        this.numeroPlacas = numeroPlacasAnt;
    }

 

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Automovil getAutomovil() {
        return automovil;
    }

    public void setAutomovil(Automovil automovil) {
        this.automovil = automovil;
    }

    @Override
    public String toString() {
        return "Placa{" + "id=" + id + ", numeroPlacasAnt=" + numeroPlacas  + ", fechaEmision=" + fechaEmision + ", fechaRecepcion=" + fechaRecepcion + ", costo=" + costo + ", automovil=" + automovil + '}';
    }

    public String getNumeroPlacas() {
        return numeroPlacas;
    }

    public void setNumeroPlacas(String numeroPlacas) {
        this.numeroPlacas = numeroPlacas;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
}
