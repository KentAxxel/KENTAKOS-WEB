package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import kentakitos.backend.entity.Pedidos;
import kentakitos.backend.repository.PedidosRepository;

public class PedidosService {
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
