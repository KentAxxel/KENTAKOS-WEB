package kentakitos.backend.service;

import java.util.List;
import java.util.Optional;
import kentakitos.backend.entity.Clientes;

public interface IClientesService {
    List<Clientes> buscarTodos();
    Clientes guardar(Clientes cliente);
    Clientes modificar(Clientes cliente);
    Optional<Clientes> buscarPorId(Integer id);
    void eliminar(Integer id);
}
