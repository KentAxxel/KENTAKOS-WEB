package kentakitos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kentakitos.backend.entity.Permisos;

public interface PermisosRepository extends JpaRepository<Permisos, Integer> {
    java.util.Optional<Permisos> findByNombrepermiso(String nombrepermiso);
}
