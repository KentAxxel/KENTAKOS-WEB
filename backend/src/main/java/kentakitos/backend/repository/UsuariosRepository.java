package kentakitos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kentakitos.backend.entity.Usuarios;

import java.util.List;
import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByUsername(String username);
    Optional<Usuarios> findByCorreo(String correo);
    @Query("SELECT r.nombre FROM UsuarioRol ur JOIN ur.rol r WHERE ur.usuario.id = :idUsuario")
    List<String> findRolesByUsuarioId(@Param("idUsuario") Integer idUsuario);
}
