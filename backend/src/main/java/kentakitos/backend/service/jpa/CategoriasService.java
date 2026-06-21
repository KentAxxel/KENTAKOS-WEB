package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;
import kentakitos.backend.entity.Categorias;
import kentakitos.backend.repository.CategoriasRepository;
import kentakitos.backend.service.ICategoriasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CategoriasService implements ICategoriasService {
    @Autowired
    private CategoriasRepository repoCategoria;

    public List<Categorias> buscarTodos(){
        return repoCategoria.findAll();
    }
    public Categorias guardar(Categorias categoria){
        return repoCategoria.save(categoria);
    }
    public Categorias modificar(Categorias categoria){
        return repoCategoria.save(categoria);
    }
    public Optional<Categorias> buscarPorId(Integer id){
        return repoCategoria.findById(id);
    }
    public void eliminar(Integer id){
        repoCategoria.deleteById(id);
    }
}
