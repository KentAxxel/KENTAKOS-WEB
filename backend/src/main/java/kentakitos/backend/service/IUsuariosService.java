package kentakitos.backend.service;

import kentakitos.backend.entity.Usuarios;
import java.util.List;
import java.util.Optional;

public interface IUsuariosService {
    List<Usuarios> buscarTodos();
    Usuarios guardar(Usuarios usuario);
    Usuarios modificar(Usuarios usuario);
    Optional<Usuarios> buscarPorId(Integer id);
    void eliminar(Integer id);

    // Métodos para seguridad
    Optional<Usuarios> buscarPorUsername(String username);
    Optional<Usuarios> buscarPorCorreo(String correo);
}