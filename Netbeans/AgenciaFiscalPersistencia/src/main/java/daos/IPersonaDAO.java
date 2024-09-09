package daos;

import entidades.Persona;
import java.util.Date;
import java.util.List;



/**
 *
 * @author delll
 */
public interface IPersonaDAO {
    
      public void RegistrarPersona(Persona persona);
      public Persona BuscarPersonaPoRFC(String rfc);
      public List<Persona> obtenerPersonas();
       public void insercion();
       public void actualizarPersona(String rfc,String nom,String ApellidoP,String ApellidoM,String tel,String curp,Date fechaN);
}
