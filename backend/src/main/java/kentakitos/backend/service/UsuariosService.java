package kentakitos.backend.service;

import kentakitos.backend.dto.UsuarioResponseDTO;
import kentakitos.backend.dto.UsuarioUpdateDTO;
import kentakitos.backend.entity.Roles;
import kentakitos.backend.entity.UsuarioRol;
import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.repository.RolesRepository;
import kentakitos.backend.repository.UsuarioRolRepository;
import kentakitos.backend.repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolesRepository rolesRepository;

    public UsuariosService(UsuariosRepository usuariosRepository, UsuarioRolRepository usuarioRolRepository, RolesRepository rolesRepository) {
        this.usuariosRepository = usuariosRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.rolesRepository = rolesRepository;
    }

    public UsuarioResponseDTO convertToDto(Usuarios usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getIdusuario());
        dto.setName(usuario.getNombre());
        dto.setUsername(usuario.getUsername());
        dto.setEmail(usuario.getCorreo());
        dto.setAuthProvider(usuario.getAuthProvider());
        dto.setSessionToken(usuario.getSessionToken());
        
        // Determinar estado
        dto.setStatus(usuario.getDeleted() == 1 ? "Activo" : "Inactivo");
        
        // Buscar roles de este usuario
        List<UsuarioRol> rolesUsuario = usuarioRolRepository.findByUsuario_Idusuario(usuario.getIdusuario());
        if (!rolesUsuario.isEmpty()) {
            // Tomamos el nombre del primer rol encontrado (para simplificar en la tabla)
            dto.setRole(rolesUsuario.get(0).getRol().getNombrerol());
            dto.setRoleId(rolesUsuario.get(0).getRol().getIdrol());
        } else {
            dto.setRole("Sin Rol");
            dto.setRoleId(null);
        }
        
        // Asignar un avatar predeterminado simple
        dto.setAvatar("👤");
        
        return dto;
    }

    public List<UsuarioResponseDTO> getAllUsuarios() {
        List<Usuarios> usuarios = usuariosRepository.findAll();
        return usuarios.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public UsuarioResponseDTO updateUsuario(Integer id, UsuarioUpdateDTO dto) {
        Usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setUsername(dto.getUsername());

        if (!"GOOGLE".equalsIgnoreCase(usuario.getAuthProvider())) {
            usuario.setCorreo(dto.getCorreo());
            if (dto.getContrasena() != null && !dto.getContrasena().trim().isEmpty()) {
                usuario.setContrasena(dto.getContrasena());
            }
        }

        usuariosRepository.save(usuario);

        // Update role if provided
        if (dto.getRolId() != null) {
            Roles rol = rolesRepository.findById(dto.getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

            List<UsuarioRol> rolesUsuario = usuarioRolRepository.findByUsuario_Idusuario(id);
            if (!rolesUsuario.isEmpty()) {
                UsuarioRol usuarioRol = rolesUsuario.get(0);
                usuarioRol.setRol(rol);
                usuarioRolRepository.save(usuarioRol);
            } else {
                UsuarioRol nuevoUsuarioRol = new UsuarioRol();
                nuevoUsuarioRol.setUsuario(usuario);
                nuevoUsuarioRol.setRol(rol);
                usuarioRolRepository.save(nuevoUsuarioRol);
            }
        }

        return getAllUsuarios().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    @Transactional
    public void deleteUsuario(Integer id) {
        usuariosRepository.deleteById(id);
    }
}
