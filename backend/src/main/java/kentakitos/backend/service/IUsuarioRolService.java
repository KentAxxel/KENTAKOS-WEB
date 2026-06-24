package kentakitos.backend.service;

import java.util.List;
import java.util.Optional;

import kentakitos.backend.entity.UsuarioRol;

public interface IUsuarioRolService {
    List<UsuarioRol> buscarTodos();
    UsuarioRol guardar(UsuarioRol categoria);
    UsuarioRol modificar(UsuarioRol categoria);
    Optional<UsuarioRol> buscarPorId (Integer id);
    void eliminar(Integer id);
}
