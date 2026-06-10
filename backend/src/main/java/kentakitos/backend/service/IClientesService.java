package kentakitos.backend.service;

import kentakitos.backend.entity.Clientes;
import java.util.List;
import java.util.Optional;

public interface IClientesService {
    List<Clientes> buscarTodos();
    Clientes guardar(Clientes cliente);
    Clientes modificar(Clientes cliente);
    Optional<Clientes> buscarId(Integer id);
    void eliminar(Integer id);
}
