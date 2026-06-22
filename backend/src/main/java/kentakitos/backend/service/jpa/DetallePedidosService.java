package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import kentakitos.backend.entity.DetallePedido;
import kentakitos.backend.repository.DetallePedidoRepository;
import kentakitos.backend.service.IDetallePedidoService;

public class DetallePedidosService implements IDetallePedidoService {
    @Autowired
    private DetallePedidoRepository repoDetallePedido;

    public List<DetallePedido> buscarTodos(){
        return repoDetallePedido.findAll();
    }
    public DetallePedido guardar(DetallePedido detallePedido){
        return repoDetallePedido.save(detallePedido);
    }
    public DetallePedido modificar(DetallePedido detallePedido){
        return repoDetallePedido.save(detallePedido);
    }
    public Optional<DetallePedido> buscarPorId(Integer id){
        return repoDetallePedido.findById(id);
    }
    public void eliminar(Integer id){
        repoDetallePedido.deleteById(id);
    }
}
