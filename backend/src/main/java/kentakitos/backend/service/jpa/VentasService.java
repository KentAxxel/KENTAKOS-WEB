package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import kentakitos.backend.entity.Ventas;
import kentakitos.backend.repository.VentasRepository;

public class VentasService {
    @Autowired
    private VentasRepository repoVentas;

    public List<Ventas> buscarTodos(){
        return repoVentas.findAll();
    }
    public Ventas guardar(Ventas venta){
        return repoVentas.save(venta);
    }
    public Ventas modificar(Ventas venta){
        return repoVentas.save(venta);
    }
    public Optional<Ventas> buscarPorId(Integer id){
        return repoVentas.findById(id);
    }
    public void eliminar(Integer id){
        repoVentas.deleteById(id);
    }
}
