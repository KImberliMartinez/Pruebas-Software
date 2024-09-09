package consultas;

import dtos.LicenciaDTO;
import entidades.Licencia;
import entidades.Persona;
import java.util.Date;
import java.util.List;

/**
 *
 * @author delll
 */
public interface IConsultasLicencias {
    public void registroLicencia(LicenciaDTO l);
    public Persona  BuscaPersonaPorRFC(String rfc);
    public List<LicenciaDTO> listaLicencias(String rfc);
    public Licencia obtenerEstadoLicencia(String rfc);
    public void actualizarLicencia(long idLicencia,Date nuevaFecha,boolean nuevoEstado);
    public List<LicenciaDTO> obtenerDetallesLicencias();
}
