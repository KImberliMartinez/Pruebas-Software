package daos;

import entidades.Automovil;
import entidades.Licencia;
import entidades.Placa;
import java.util.Date;
import java.util.List;

/**
 *
 * @author delll
 */
public interface IPlacasDAO {
    public void AgregarPlacasNuevas(Placa p,Automovil a);
    public void AgregarPlacasUsadas(Placa p);
    public Automovil BuscarPlacas(String p);
    public List<Placa> obtenerPlacasPorRfc(String rfc);
    public void actualizarPlacas(long id,Date FechaR,boolean nuevoEstado);
    public List<Placa> obtenerDetallesPlacas();
    //public int buscarAutoPorId(int id);
}
