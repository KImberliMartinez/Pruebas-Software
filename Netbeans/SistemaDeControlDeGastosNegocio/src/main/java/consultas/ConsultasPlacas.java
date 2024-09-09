
package consultas;

import daos.IPlacasDAO;
import daos.PlacasDAO;

import dtos.AutomovilesDTO;
import dtos.LicenciaDTO;

import dtos.PlacasDTO;
import entidades.Automovil;
import entidades.Persona;
import entidades.Placa;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author delll
 */
public class ConsultasPlacas implements IConsultasPlacas {

    IPlacasDAO placas;
    IConsultasLicencias rfc;
    Persona persona;

    public ConsultasPlacas() {
        placas = new PlacasDAO();
        rfc = new ConsultasLicencias();
    }

    @Override
    public void AgregarPlacasNuevas(PlacasDTO p) {
        if (rfc.BuscaPersonaPorRFC(p.getAuto().getRfc()) != null) {
            persona = rfc.BuscaPersonaPorRFC(p.getAuto().getRfc());
            Automovil auto = new Automovil(p.getAuto().getNumeroSerie(), p.getAuto().getMarca(), p.getAuto().getLinea(), p.getAuto().getColor(), p.getAuto().getModelo(), persona);
            Placa placa = new Placa(p.getNumeroPlacas(), p.getFechaEmision(), null, p.getCosto(), auto,p.isActiva());
            placas.AgregarPlacasNuevas(placa, auto);
        } else {
            throw new IllegalArgumentException("El rfc que busca no existe");
        }
    }

    @Override
    public void AgregarPlacasUsadas(PlacasDTO p,String placaVieja) {
       Automovil auto= placas.BuscarPlacas(placaVieja);
        Placa placa = new Placa(p.getNumeroPlacas(), p.getFechaEmision(), null, p.getCosto(), auto, true);
            placas.AgregarPlacasUsadas(placa);
    }

    @Override
    public AutomovilesDTO BuscarPlacas(String p) {
        Automovil a=placas.BuscarPlacas(p);
        if(a!=null){
        AutomovilesDTO mandar=new AutomovilesDTO(a.getNumeroSerie(), a.getMarca(),a.getLinea(),a.getColor(),a.getModelo(),a.getPersona().getRfc());
       mandar.setIdAuto(a.getId());
       return  mandar;
        }else{
            return null;
        }
    }

    @Override
    public List<PlacasDTO> listaPlacas(String rfc) {
        List<Placa> placa = placas.obtenerPlacasPorRfc(rfc); // Obtener placas desde la base de datos
        return convertirADTOsPlaca(placa);
    }

  

    private List<PlacasDTO> convertirADTOsPlaca(List<Placa> placas) {
        List<PlacasDTO> pDTO = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateadaE;
        String fechaFormateadaR;
        for (Placa p : placas) {
            PlacasDTO placaDTO = new PlacasDTO();
            if(p.getFechaEmision()!=null){
             fechaFormateadaE = sdf.format(p.getFechaEmision());
             placaDTO.setNumeroPlacas(p.getNumeroPlacasAnt());
            placaDTO.setFechaE(fechaFormateadaE);
            placaDTO.setFechaR("");
            placaDTO.setCosto(p.getCosto());
            placaDTO.setIdAuto(p.getAutomovil().getId());
            placaDTO.setActiva(p.isActiva());
            

            pDTO.add(placaDTO);
            }
            if(p.getFechaRecepcion()!=null){
             fechaFormateadaR = sdf.format(p.getFechaRecepcion());
              placaDTO.setNumeroPlacas(p.getNumeroPlacasAnt());
            placaDTO.setFechaE("");
            placaDTO.setFechaR(fechaFormateadaR);
            placaDTO.setCosto(p.getCosto());
            placaDTO.setIdAuto(p.getAutomovil().getId());
            placaDTO.setActiva(p.isActiva());

            pDTO.add(placaDTO);
            }
         
            
           
        }
        return pDTO;
    }

    @Override
    public void actualizarPlacas(long id, Date FechaR, boolean nuevoEstado) {
        placas.actualizarPlacas(id, FechaR, nuevoEstado);
    }
    
    @Override
    public List<PlacasDTO> obtenerInformacionPlacas() {
        List<Placa> placas = this.placas.obtenerDetallesPlacas();
        List<PlacasDTO> placasInfo = new ArrayList<>();

        for (Placa p : placas) {
            PlacasDTO placaDTO = new PlacasDTO();
            placaDTO.setNumeroPlacas(p.getNumeroPlacas());
            placaDTO.setFechaEmision(p.getFechaEmision());
            placaDTO.setFechaRecepcion(p.getFechaRecepcion());
            placaDTO.setCosto(p.getCosto());
            placaDTO.setAuto(new AutomovilesDTO(p.getAutomovil().getNumeroSerie(), p.getAutomovil().getMarca(), p.getAutomovil().getLinea(), p.getAutomovil().getColor(), p.getAutomovil().getModelo(), p.getAutomovil().getPersona().getRfc()));
            placaDTO.setActiva(p.isActiva());
            placaDTO.setRfcDueno(placaDTO.getAuto().getRfc());

            placasInfo.add(placaDTO);
        }

        return placasInfo;
    }
    

   
}
