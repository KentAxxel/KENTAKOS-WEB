package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kentakitos.backend.entity.Pedidos;
import kentakitos.backend.repository.PedidosRepository;
import kentakitos.backend.service.IPedidosService;

@Service

public class PedidosService implements IPedidosService {
    @Autowired
    private PedidosRepository repoPedidos;

    public List<Pedidos> buscarTodos(){
        return repoPedidos.findAll();
    }
    public Pedidos guardar(Pedidos pedido){
        return repoPedidos.save(pedido);
    }
    public Pedidos modificar(Pedidos pedido){
        return repoPedidos.save(pedido);
    }
    public Optional<Pedidos> buscarPorId(Integer id){
        return repoPedidos.findById(id);
    }
    public void eliminar(Integer id){
        repoPedidos.deleteById(id);
    }
}
