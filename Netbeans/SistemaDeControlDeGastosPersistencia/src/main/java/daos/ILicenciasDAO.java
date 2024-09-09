package daos;

import entidades.Licencia;
import entidades.Persona;
import java.util.Date;
import java.util.List;



/**
 *
 * @author delll
 */
public interface ILicenciasDAO {

  public void RegistrarLicencia(Licencia licencia);
  public Persona BuscarPersonaPoRFC(String rfc);
  public List<Licencia> obtenerLicenciasPorRfc(String rfc);
  public Licencia obtenerLicencia(String rfc);
  public void actualizarLicencias(long idLicencia,Date nuevaFecha,boolean nuevoEstado);
  public List<Licencia> obtenerTodasLasLicencias();
}
