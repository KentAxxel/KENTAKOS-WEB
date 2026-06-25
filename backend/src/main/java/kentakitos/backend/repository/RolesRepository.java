package kentakitos.backend.repository;

import kentakitos.backend.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByNombrerol(String nombre);
}