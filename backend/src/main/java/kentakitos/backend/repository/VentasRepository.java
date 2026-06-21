package kentakitos.backend.repository;

import kentakitos.backend.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentasRepository extends JpaRepository<Ventas, Integer> {
    
}
