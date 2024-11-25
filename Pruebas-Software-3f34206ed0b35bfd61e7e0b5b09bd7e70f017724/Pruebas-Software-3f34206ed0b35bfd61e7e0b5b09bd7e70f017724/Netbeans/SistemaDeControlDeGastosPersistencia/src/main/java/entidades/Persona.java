package entidades;

import static Encriptacion.EncriptacionDatos.decriptar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author molin
 */
@Entity
@Table(name = "personas")
public class Persona implements Serializable {

    
     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rfc", nullable = false, length = 13,unique=true)
    private String rfc;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "apellidoP", nullable = false, length = 255)
    private String apellidoP;

    @Column(name = "apellidoM", nullable = false, length = 255)
    private String apellidoM;

    @Column(name = "telefono", nullable = false, length = 255)
    private String telefono;

    

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    @Column(name = "curp", nullable = false, length = 18)
    private String curp;

     @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Licencia> licencias ;


    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Automovil> automoviles;
    
    public Persona() {
        automoviles = new ArrayList<>();
        licencias = new ArrayList<>();
    }
    

    public Persona(String nombre, String apellidoP, String apellidoM, String telefono, String rfc, Date fechaNacimiento, String curp) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.telefono = telefono;
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.curp = curp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    public List<Licencia> getLicencias() {
        return licencias;
    }

    public void setLicencias(List<Licencia> licencias) {
        this.licencias = licencias;
    }

    public List<Automovil> getAutomoviles() {
        return automoviles;
    }

    public void setAutomoviles(List<Automovil> automoviles) {
        this.automoviles = automoviles;
    }

    public String getDesencriptado(){
        return decriptar(telefono);
    }    
    @Override
    public String toString() {
        return "Persona{" + "rcf=" + rfc + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", telefono=" + telefono + ", fechaNacimiento=" + fechaNacimiento + ", curp=" + curp + '}';
    }

    

}
