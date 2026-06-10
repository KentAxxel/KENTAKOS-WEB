package kentakitos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kentakitos.backend.entity.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {
    
}
