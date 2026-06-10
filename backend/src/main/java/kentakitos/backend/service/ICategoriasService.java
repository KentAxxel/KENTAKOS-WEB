package kentakitos.backend.service;

import java.util.List;
import java.util.Optional;
import kentakitos.backend.entity.Categorias;

public interface ICategoriasService {
    List<Categorias> buscarTodos();
    Categorias guardar(Categorias categoria);
    Categorias modificar(Categorias categoria);
    Optional<Categorias> buscarId(Integer id);
    void eliminar(Integer id);
}
