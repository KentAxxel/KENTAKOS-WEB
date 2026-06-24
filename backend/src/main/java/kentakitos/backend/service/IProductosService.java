package kentakitos.backend.service;

import java.util.List;
import java.util.Optional; 
import kentakitos.backend.entity.Productos;

public interface IProductosService {
    List<Productos> buscarTodos();
    Productos guardar(Productos producto);
    Productos modificar(Productos producto);
    Optional<Productos> buscarPorId(Integer id);
    void eliminar(Integer id);
}
