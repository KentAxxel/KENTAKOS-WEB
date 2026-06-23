package kentakitos.backend.service;

import java.util.List;
import java.util.Optional;
import kentakitos.backend.entity.Usuarios;;

public interface IUsuariosService {
    List<Usuarios> buscarTodos();
    Usuarios guardar(Usuarios usuario);
    Usuarios modificar(Usuarios usuario);
    Optional<Usuarios> buscarPorId(Integer id);
    void eliminar(Integer id);
}
