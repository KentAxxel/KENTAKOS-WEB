package kentakitos.backend.service.jpa;

import kentakitos.backend.entity.Roles;
import kentakitos.backend.entity.UsuarioRol;
import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.repository.RolesRepository; 
import kentakitos.backend.repository.UsuarioRolRepository;
import kentakitos.backend.service.IUsuarioRolService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioRolService implements IUsuarioRolService {

    private final UsuarioRolRepository repoUsuarioRol;
    private final RolesRepository repoRoles;           // para obtener el rol INVITADO

    public UsuarioRolService(UsuarioRolRepository repoUsuarioRol, RolesRepository repoRoles) {
        this.repoUsuarioRol = repoUsuarioRol;
        this.repoRoles = repoRoles;
    }

    @Override
    public List<UsuarioRol> buscarTodos() {
        return repoUsuarioRol.findAll();
    }

    @Override
    public UsuarioRol guardar(UsuarioRol usuarioRol) {
        return repoUsuarioRol.save(usuarioRol);
    }

    @Override
    public UsuarioRol modificar(UsuarioRol usuarioRol) {
        return repoUsuarioRol.save(usuarioRol);
    }

    @Override
    public Optional<UsuarioRol> buscarPorId(Integer id) {
        return repoUsuarioRol.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        repoUsuarioRol.deleteById(id);
    }

    @Override
    public List<String> buscarRolesPorUsuario(Integer idUsuario) {
        return repoUsuarioRol.findRolesByUsuarioId(idUsuario);
    }

    @Override
    public void asignarRolInvitado(Integer idUsuario) {
        Roles rolInvitado = repoRoles.findByNombrerol("INVITADO")
                .orElseThrow(() -> new RuntimeException("Rol INVITADO no encontrado en la BD"));

        UsuarioRol ur = new UsuarioRol();
        // Suponiendo que tus setters aceptan la entidad (como en tu controller)
        ur.setIdusuario(new Usuarios(idUsuario));
        ur.setIdrol(rolInvitado);
        repoUsuarioRol.save(ur);
    }
}