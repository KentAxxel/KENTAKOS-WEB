package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kentakitos.backend.entity.Inventario;
import kentakitos.backend.repository.InventarioRepository;
import kentakitos.backend.service.IInventarioService;

@Service

public class InventarioService implements IInventarioService {
    @Autowired
    private InventarioRepository repoInventario;

    public List<Inventario> buscarTodos(){
        return repoInventario.findAll();
    }
    public Inventario guardar(Inventario inventario){
        return repoInventario.save(inventario);
    }
    public Inventario modificar(Inventario inventario){
        return repoInventario.save(inventario);
    }
    public Optional<Inventario> buscarPorId(Integer id){
        return repoInventario.findById(id);
    }
    public void eliminar(Integer id){
        repoInventario.deleteById(id);
    }
}
