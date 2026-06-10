package kentakitos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kentakitos.backend.entity.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {
    
}
