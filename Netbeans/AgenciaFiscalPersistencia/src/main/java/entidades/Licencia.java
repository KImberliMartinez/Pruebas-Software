
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "Licencias")
public class Licencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RFC", length = 13)
    String RFC;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Enumerated(EnumType.STRING)
    private EnumTipoLicencia tipo;

    @Enumerated(EnumType.STRING)
    private EnumVigenciaLicencia vigencia;

    @Column(name = "FechaVencimiento")
    @Temporal(TemporalType.DATE)
    private Date FechaVencimiento;
    
    @Column(name = "Activa")
    private boolean activa;
    
    public Licencia() {
    }

    public Licencia(String RFC, Persona persona, EnumTipoLicencia tipo, EnumVigenciaLicencia vigencia, Date FechaVencimiento, boolean activa) {
        this.RFC = RFC;
        this.persona = persona;
        this.tipo = tipo;
        this.vigencia = vigencia;
        this.FechaVencimiento = FechaVencimiento;
        this.activa = activa;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Date getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(Date FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public EnumTipoLicencia getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoLicencia tipo) {
        this.tipo = tipo;
    }

    public EnumVigenciaLicencia getVigencia() {
        return vigencia;
    }

    public void setVigencia(EnumVigenciaLicencia vigencia) {
        this.vigencia = vigencia;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "Licencia{" + "id=" + id + ", RFC=" + RFC + ", persona=" + persona + ", tipo=" + tipo + ", vigencia=" + vigencia + ", FechaVencimiento=" + FechaVencimiento + ", activa=" + activa + '}';
    }

    

}
