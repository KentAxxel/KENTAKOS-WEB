package kentakitos.backend.service;

import kentakitos.backend.entity.DetallePedido;
import java.util.List;
import java.util.Optional;

public interface IDetallePedidoService {
    List<DetallePedido> buscarTodos();
    DetallePedido guardar(DetallePedido detallePedido);
    DetallePedido modificar(DetallePedido detallePedido);
    Optional<DetallePedido> buscarPorId(Integer id);
    void eliminar(Integer id);
}
