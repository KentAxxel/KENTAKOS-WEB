package kentakitos.backend.service;

import java.util.List;
import java.util.Optional; 
import kentakitos.backend.entity.Roles;

public interface IRolesService {
    List<Roles> buscarTodos();
    Roles guardar(Roles rol);
    Roles modificar(Roles rol);
    Optional<Roles> buscarPorId(Integer id);
    void eliminar(Integer id);
    Roles findByNombreRol(String nombreRol);
}
