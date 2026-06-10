package kentakitos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kentakitos.backend.entity.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    
}
