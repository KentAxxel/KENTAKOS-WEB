package kentakitos.backend.service;

import java.util.List;
import java.util.Optional;
import kentakitos.backend.entity.Ventas;

public interface IVentasService {
    List<Ventas> buscarTodos();
    Ventas guardar(Ventas venta);
    Ventas modificar(Ventas venta);
    Optional<Ventas> buscarPorId(Integer id);
    void eliminar(Integer id);
}
