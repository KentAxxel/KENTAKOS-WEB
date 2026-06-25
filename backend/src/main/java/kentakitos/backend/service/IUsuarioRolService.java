package kentakitos.backend.service;

import kentakitos.backend.entity.UsuarioRol;
import java.util.List;
import java.util.Optional;

public interface IUsuarioRolService {
    List<UsuarioRol> buscarTodos();
    UsuarioRol guardar(UsuarioRol usuarioRol);
    UsuarioRol modificar(UsuarioRol usuarioRol);
    Optional<UsuarioRol> buscarPorId(Integer id);
    void eliminar(Integer id);

    // Métodos para seguridad
    List<String> buscarRolesPorUsuario(Integer idUsuario);
    void asignarRolInvitado(Integer idUsuario);
}