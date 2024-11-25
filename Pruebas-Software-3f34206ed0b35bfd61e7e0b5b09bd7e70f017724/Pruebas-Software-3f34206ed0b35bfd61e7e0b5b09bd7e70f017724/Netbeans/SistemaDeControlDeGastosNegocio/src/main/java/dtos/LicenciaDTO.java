package dtos;

import entidades.EnumTipoLicencia;
import entidades.EnumVigenciaLicencia;
import java.util.Date;

/**
 *
 * @author delll
 */
public class LicenciaDTO {

    Long id;
    String RFC;
    EnumTipoLicencia tipo;
    EnumVigenciaLicencia vigencia;
    Float costo;
    Date FechaVencimiento;
    Date FechaRegistro;
    boolean activa;
    String Fv;

    public LicenciaDTO(String RFC, EnumTipoLicencia tipo, EnumVigenciaLicencia vigencia, Float costo, Date FechaVencimiento, boolean activa) {
        this.RFC = RFC;
        this.tipo = tipo;
        this.vigencia = vigencia;
        this.costo = costo;
        this.FechaVencimiento = FechaVencimiento;
        this.activa = activa;
    }

    public LicenciaDTO(String RFC, EnumTipoLicencia tipo, EnumVigenciaLicencia vigencia, Float costo, Date FechaVencimiento, Date FechaRegistro, boolean activa) {
        this.RFC = RFC;
        this.tipo = tipo;
        this.vigencia = vigencia;
        this.costo = costo;
        this.FechaVencimiento = FechaVencimiento;
        this.FechaRegistro = FechaRegistro;
        this.activa = activa;
    }

    public Date getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(Date FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }
    
    public LicenciaDTO() {
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
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

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Date getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(Date FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFv() {
        return Fv;
    }

    public void setFv(String Fv) {
        this.Fv = Fv;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

}
