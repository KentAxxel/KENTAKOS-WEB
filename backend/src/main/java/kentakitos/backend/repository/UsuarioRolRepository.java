package kentakitos.backend.repository;

import kentakitos.backend.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Integer> {
    List<UsuarioRol> findByUsuario_Idusuario(Integer idusuario);
}
