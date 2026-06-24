package kentakitos.backend.service;

import java.util.List;
import java.util.Optional;

import kentakitos.backend.entity.Pedidos;

public interface IPedidosService {
    List<Pedidos> buscarTodos();
    Pedidos guardar(Pedidos pedido);
    Pedidos modificar(Pedidos pedido);
    Optional<Pedidos> buscarPorId(Integer id);
    void eliminar(Integer id);
}
