package kentakitos.backend.service;

import java.util.List;
import java.util.Optional;
import kentakitos.backend.entity.DetalleVenta;

public interface IDetalleVentaService {
    List<DetalleVenta> buscarTodos();
    DetalleVenta guardar(DetalleVenta detalleVenta);
    DetalleVenta modificar(DetalleVenta detalleVenta);
    Optional<DetalleVenta> buscarPorId(Integer id);
    void eliminar(Integer id);
}
