package kentakitos.backend.service;

import java.util.List;
import java.util.Optional; 
import kentakitos.backend.entity.Mesas;

public interface IMesasService {
    List<Mesas> buscarTodos();
    Mesas guardar(Mesas mesas);
    Mesas modificar(Mesas mesas);
    Optional<Mesas> buscarId(Integer id);
    void eliminar(Integer id);
}
