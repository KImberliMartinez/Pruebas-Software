package consultas;

import daos.ILicenciasDAO;
import daos.LicenciasDAO;
import dtos.LicenciaDTO;
import entidades.EnumCostos;
import entidades.EnumTipoLicencia;
import static entidades.EnumTipoLicencia.DISCAPACITADOS;
import static entidades.EnumTipoLicencia.NORMAL;
import entidades.EnumVigenciaLicencia;
import static entidades.EnumVigenciaLicencia.DOS;
import static entidades.EnumVigenciaLicencia.TRES;
import static entidades.EnumVigenciaLicencia.UNO;
import entidades.Licencia;
import entidades.Persona;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author delll
 */
public class ConsultasLicencias implements IConsultasLicencias {
    ILicenciasDAO licencias;
    Persona persona;

    public ConsultasLicencias() {
        licencias=new LicenciasDAO();
    }
    
    @Override
    public void registroLicencia(LicenciaDTO l) {
        persona=BuscaPersonaPorRFC(l.getRFC());
         Licencia nuevaLicencia=new Licencia(l.getRFC(), persona,l.getTipo(),l.getVigencia(),l.getFechaVencimiento(),l.isActiva());
        System.out.println("consultaLicencias");
        licencias.RegistrarLicencia(nuevaLicencia);
    }

    @Override
    public Persona BuscaPersonaPorRFC(String rfc) {
        return licencias.BuscarPersonaPoRFC(rfc);
        
    }
    
      @Override
    public List<LicenciaDTO> listaLicencias(String rfc) {
    List<Licencia> lic = licencias.obtenerLicenciasPorRfc(rfc); // Obtener licencias desde la base de datos
        return convertirADTOsLicencia(lic);    
    }

    

    private List<LicenciaDTO> convertirADTOsLicencia(List<Licencia> licencias) {
        List<LicenciaDTO> lDTO = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Licencia p : licencias) {
            LicenciaDTO licenciaDTO = new LicenciaDTO();
            String Fechavencimiento = sdf.format(p.getFechaVencimiento());


          licenciaDTO.setId(p.getId());
          licenciaDTO.setRFC(p.getRFC());
          licenciaDTO.setFv(Fechavencimiento);
          licenciaDTO.setVigencia(p.getVigencia());
          licenciaDTO.setTipo(p.getTipo());
          licenciaDTO.setActiva(p.isActiva());
          

            lDTO.add(licenciaDTO);
        }
        return lDTO;
    }

    @Override
    public Licencia obtenerEstadoLicencia(String rfc) {
        return  licencias.obtenerLicencia(rfc);
    }

    @Override
    public void actualizarLicencia(long idLicencia,Date nuevaFecha,boolean nuevoEstado) {
        licencias.actualizarLicencias(idLicencia,nuevaFecha,nuevoEstado);
    }
    @Override
    public List<LicenciaDTO> obtenerDetallesLicencias() {
        List<Licencia> licenciasRegistradas = licencias.obtenerTodasLasLicencias(); // Este método debe ser implementado en la clase LicenciasDAO
        List<LicenciaDTO> detallesLicencias = new ArrayList<>();

        for (Licencia licencia : licenciasRegistradas) {
            LicenciaDTO licenciaDTO = new LicenciaDTO();
            licenciaDTO.setId(licencia.getId());
            licenciaDTO.setRFC(licencia.getRFC()); // RFC de la persona a la que pertenece la licencia
            licenciaDTO.setTipo(licencia.getTipo());
            licenciaDTO.setVigencia(licencia.getVigencia());
            licenciaDTO.setFechaVencimiento(licencia.getFechaVencimiento());
            licenciaDTO.setActiva(licencia.isActiva());
            // Usa el costo que ya está almacenado en el LicenciaDTO
            licenciaDTO.setCosto(calcularCosto(licencia.getTipo(), licencia.getVigencia()));
            // Calcula la fecha de registro restando la duración de la vigencia a la fecha de vigencia
            Calendar c = Calendar.getInstance();
            c.setTime(licencia.getFechaVencimiento());
            c.add(Calendar.YEAR, -licencia.getVigencia().getAnios());
            Date fechaRegistro = c.getTime();
            licenciaDTO.setFechaRegistro(fechaRegistro);

            detallesLicencias.add(licenciaDTO);
        }

        return detallesLicencias;
    }

    private float calcularCosto(EnumTipoLicencia tipo, EnumVigenciaLicencia vigencia) {
        switch (tipo) {
            case NORMAL:
                switch (vigencia) {
                    case UNO:
                        return EnumCostos.UN_ANIO_NORMAL.getCosto();
                    case DOS:
                        return EnumCostos.DOS_ANIOS_NORMAL.getCosto();
                    case TRES:
                        return EnumCostos.TRES_ANIOS_NORMAL.getCosto();
                }
                break;
            case DISCAPACITADOS:
                switch (vigencia) {
                    case UNO:
                        return EnumCostos.UN_ANIO_DISCAPACITADOS.getCosto();
                    case DOS:
                        return EnumCostos.DOS_ANIOS_DISCAPACITADOS.getCosto();
                    case TRES:
                        return EnumCostos.TRES_ANIOS_DISCAPACITADOS.getCosto();
                }
                break;
        }
        throw new IllegalArgumentException("Tipo de licencia o vigencia no válidos.");
    }
    
}

    

