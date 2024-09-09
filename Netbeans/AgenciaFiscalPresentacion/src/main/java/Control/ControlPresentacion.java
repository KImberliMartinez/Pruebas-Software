package Control;

import agenciafiscalpresentacion.ActualizarPersona;
import agenciafiscalpresentacion.DlgConsultas;
import agenciafiscalpresentacion.DlgHistorial;
import agenciafiscalpresentacion.DlgLicencia;
import agenciafiscalpresentacion.DlgPlacasNuevas;
import agenciafiscalpresentacion.DlgPlacasUsadas;
import agenciafiscalpresentacion.DlgRegistro;
import agenciafiscalpresentacion.DlgReporte;
import agenciafiscalpresentacion.Menu;
import consultas.ConsultasLicencias;
import consultas.ConsultasPlacas;
import java.util.List;

/**
 *
 * @author molin
 */
public class ControlPresentacion {

    public ControlPresentacion() {
    }

    public void desplegarMenu() {
        Menu desplegarMenu = new Menu();
    }

    public void desplegarRegistro() {
        DlgRegistro desplegarRegistro = new DlgRegistro();
    }

    public void desplegarDlgLicencia(String nombreSolicitante, String rfc) {
        DlgLicencia dlgLicencia = new DlgLicencia();

        dlgLicencia.mostrarNombreSolicitante(nombreSolicitante);
        dlgLicencia.mostrarRFC(rfc);
        dlgLicencia.setVisible(true);

    }

    public void desplegarDlgLicencia() {
        DlgLicencia dlgLicencia = new DlgLicencia();
    }

    public void desplegarDlgPlacasNuevas() {
        DlgPlacasNuevas dlgPlacasNuevas = new DlgPlacasNuevas();
    }

    public void desplegarDlgPlacasUsadas() {
        DlgPlacasUsadas dlgPlacasUsadas = new DlgPlacasUsadas();

    }

    public void desplegarDlgReporte(){
        DlgReporte dlgReporte = new DlgReporte();
    }
    public void desplegarDlgConsultas(){
        DlgConsultas dlgConsultas=new DlgConsultas();
    }
    public void desplegarDlgHistorial(String rfc){
        DlgHistorial dlgHistorial=new DlgHistorial(rfc);
    }
    
    public List obtenerDetallesLicencias() {
        ConsultasLicencias c = new ConsultasLicencias();
        return c.obtenerDetallesLicencias();
    }
    public List obtenerDetallesPlacas() {
        ConsultasPlacas c = new ConsultasPlacas();
        return c.obtenerInformacionPlacas();
    }
    
    public void desplegarDlgActualizacion() {
        ActualizarPersona dlgActualizar = new ActualizarPersona();
    }

}
