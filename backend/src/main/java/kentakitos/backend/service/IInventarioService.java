package kentakitos.backend.service;

import java.util.List;
import java.util.Optional;
import kentakitos.backend.entity.Inventario;

public interface IInventarioService {
    List<Inventario> buscarTodos();
    Inventario guardar(Inventario inventario);
    Inventario modificar(Inventario inventario);
    Optional<Inventario> buscarId(Integer id);
    void eliminar(Integer id);
}
