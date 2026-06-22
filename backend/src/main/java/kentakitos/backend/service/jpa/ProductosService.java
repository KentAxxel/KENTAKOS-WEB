package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import kentakitos.backend.entity.Productos;
import kentakitos.backend.repository.ProductosRepository;

public class ProductosService {
    @Autowired
    private ProductosRepository repoProductos;

    public List<Productos> buscarTodos(){
        return repoProductos.findAll();
    }
    public Productos guardar(Productos producto){
        return repoProductos.save(producto);
    }
    public Productos modificar(Productos producto){
        return repoProductos.save(producto);
    }
    public Optional<Productos> buscarPorId(Integer id){
        return repoProductos.findById(id);
    }
    public void eliminar(Integer id){
        repoProductos.deleteById(id);
    }
}
