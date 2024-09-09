package consultas;

import dtos.PersonasDTO;
import entidades.Persona;
import java.util.Date;
import java.util.List;

/**
 *
 * @author delll
 */
public interface IConsultasPersonas {
    public void registroPersona(PersonasDTO p);
    public Persona BuscaPersonaPorRFC(String rfc);
    public List<PersonasDTO> obtenerListaPersonas();
    public List<PersonasDTO> convertirPersonasADTOs(List<Persona> personas);
    public void insercion();
    public void actalizacionPersona(String rfc,String nom,String ApellidoP,String ApellidoM,String tel,String curp,Date fechaN);
}
