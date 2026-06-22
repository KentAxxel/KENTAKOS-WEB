package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import kentakitos.backend.entity.DetalleVenta;
import kentakitos.backend.repository.DetalleVentasRepository;
import kentakitos.backend.service.IDetalleVentaService;

public class DetalleVentasService implements IDetalleVentaService {
    @Autowired
    private DetalleVentasRepository repoDetalleVentas;

    public List<DetalleVenta> buscarTodos(){
        return repoDetalleVentas.findAll();
    }
    public DetalleVenta guardar(DetalleVenta detalleVenta){
        return repoDetalleVentas.save(detalleVenta);
    }
    public DetalleVenta modificar(DetalleVenta detalleVenta){
        return repoDetalleVentas.save(detalleVenta);
    }
    public Optional<DetalleVenta> buscarPorId(Integer id){
        return repoDetalleVentas.findById(id);
    }
    public void eliminar(Integer id){
        repoDetalleVentas.deleteById(id);
    }
}
