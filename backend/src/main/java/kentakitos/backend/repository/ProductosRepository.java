package kentakitos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kentakitos.backend.entity.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {
    
}
