package kentakitos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kentakitos.backend.entity.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
    
}
