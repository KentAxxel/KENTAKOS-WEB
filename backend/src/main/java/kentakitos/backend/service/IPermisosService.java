package kentakitos.backend.service;

import kentakitos.backend.entity.Permisos;

import java.util.List;
import java.util.Optional;

public interface IPermisosService {
    List<Permisos> buscarTodos();
    Permisos guardar(Permisos permiso);
    Permisos modificar(Permisos permiso);
    Optional<Permisos> buscarId(Integer id);
    void eliminar(Integer id);
}
