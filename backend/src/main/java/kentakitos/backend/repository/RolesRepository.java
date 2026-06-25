package kentakitos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kentakitos.backend.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    java.util.Optional<Roles> findByNombrerol(String nombrerol);
}
