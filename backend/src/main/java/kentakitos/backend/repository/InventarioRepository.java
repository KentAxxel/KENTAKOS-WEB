package kentakitos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kentakitos.backend.entity.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    
}
