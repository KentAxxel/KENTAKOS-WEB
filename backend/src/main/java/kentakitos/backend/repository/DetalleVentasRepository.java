package kentakitos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kentakitos.backend.entity.DetalleVenta;

public interface DetalleVentasRepository extends JpaRepository<DetalleVenta, Integer> {
    
}
