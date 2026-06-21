package kentakitos.backend.service;

import kentakitos.backend.dto.UsuarioResponseDTO;
import kentakitos.backend.entity.UsuarioRol;
import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.repository.UsuarioRolRepository;
import kentakitos.backend.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final UsuarioRolRepository usuarioRolRepository;

    public UsuariosService(UsuariosRepository usuariosRepository, UsuarioRolRepository usuarioRolRepository) {
        this.usuariosRepository = usuariosRepository;
        this.usuarioRolRepository = usuarioRolRepository;
    }

    public List<UsuarioResponseDTO> getAllUsuarios() {
        List<Usuarios> usuarios = usuariosRepository.findAll();
        
        return usuarios.stream().map(usuario -> {
            UsuarioResponseDTO dto = new UsuarioResponseDTO();
            dto.setId(usuario.getIdusuario());
            dto.setName(usuario.getNombre());
            dto.setEmail(usuario.getCorreo());
            
            // Determinar estado
            dto.setStatus(usuario.getDeleted() == 1 ? "Activo" : "Inactivo");
            
            // Buscar roles de este usuario
            List<UsuarioRol> rolesUsuario = usuarioRolRepository.findByUsuario_Idusuario(usuario.getIdusuario());
            if (!rolesUsuario.isEmpty()) {
                // Tomamos el nombre del primer rol encontrado (para simplificar en la tabla)
                dto.setRole(rolesUsuario.get(0).getRol().getNombrerol());
            } else {
                dto.setRole("Sin Rol");
            }
            
            // Asignar un avatar predeterminado simple
            dto.setAvatar("👤");
            
            return dto;
        }).collect(Collectors.toList());
    }
}
