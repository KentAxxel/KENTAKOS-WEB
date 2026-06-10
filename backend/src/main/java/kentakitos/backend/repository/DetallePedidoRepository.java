package kentakitos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kentakitos.backend.entity.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    
}
