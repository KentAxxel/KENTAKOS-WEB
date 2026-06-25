package kentakitos.backend.repository;

import kentakitos.backend.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Integer> {

    @Query("SELECT r.nombre FROM UsuarioRol ur JOIN ur.idrol r WHERE ur.idusuario.idusuario = :idUsuario")
    List<String> findRolesByUsuarioId(@Param("idUsuario") Integer idUsuario);
}