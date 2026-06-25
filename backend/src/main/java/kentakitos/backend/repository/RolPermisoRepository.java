package kentakitos.backend.repository;

import kentakitos.backend.entity.RolPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RolPermisoRepository extends JpaRepository<RolPermiso, Integer> {
    List<RolPermiso> findByRol_Idrol(Integer idrol);
    java.util.Optional<RolPermiso> findByRol_IdrolAndPermiso_Idpermiso(Integer idrol, Integer idpermiso);
}
