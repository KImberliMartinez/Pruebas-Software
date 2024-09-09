package dtos;


import java.util.Date;

/**
 *
 * @author delll
 */
public class PersonasDTO {
    String RFC;
    String Nombre;
    String ApellidoM;
    String ApellidoP;
    String Telefono;
    Date FechaNacimiento;
    String Curp;
    String Fecha;
    

    public PersonasDTO(String RFC, String Nombre, String ApellidoM, String ApellidoP, String Telefono, Date FechaNacimiento, String Curp) {
        this.RFC = RFC;
        this.Nombre = Nombre;
        this.ApellidoM = ApellidoM;
        this.ApellidoP = ApellidoP;
        this.Telefono = Telefono;
        this.FechaNacimiento = FechaNacimiento;
        this.Curp = Curp;
    }

    public PersonasDTO(String Nombre, String ApellidoM, String ApellidoP, String Telefono, Date FechaNacimiento, String Curp) {
        this.Nombre = Nombre;
        this.ApellidoM = ApellidoM;
        this.ApellidoP = ApellidoP;
        this.Telefono = Telefono;
        this.FechaNacimiento = FechaNacimiento;
        this.Curp = Curp;
        
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getFecha() {
        return Fecha;
    }
    

    public PersonasDTO() {
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoM() {
        return ApellidoM;
    }

    public void setApellidoM(String ApellidoM) {
        this.ApellidoM = ApellidoM;
    }

    public String getApellidoP() {
        return ApellidoP;
    }

    public void setApellidoP(String ApellidoP) {
        this.ApellidoP = ApellidoP;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public String getCurp() {
        return Curp;
    }

    public void setCurp(String Curp) {
        this.Curp = Curp;
    }
    
    
}
