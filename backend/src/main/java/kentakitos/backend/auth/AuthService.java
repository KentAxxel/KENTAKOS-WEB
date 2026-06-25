package kentakitos.backend.auth;

import kentakitos.backend.auth.dto.LoginRequest;
import kentakitos.backend.auth.dto.RegisterRequest;
import kentakitos.backend.dto.UsuarioResponseDTO;
import kentakitos.backend.entity.Roles;
import kentakitos.backend.entity.UsuarioRol;
import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.repository.RolesRepository;
import kentakitos.backend.repository.UsuarioRolRepository;
import kentakitos.backend.repository.UsuariosRepository;
import kentakitos.backend.service.UsuariosService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuariosRepository usuariosRepository;
    private final RolesRepository rolesRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final UsuariosService usuariosService;

    public AuthService(UsuariosRepository usuariosRepository, RolesRepository rolesRepository,
                       UsuarioRolRepository usuarioRolRepository, UsuariosService usuariosService) {
        this.usuariosRepository = usuariosRepository;
        this.rolesRepository = rolesRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.usuariosService = usuariosService;
    }

    @Transactional
    public UsuarioResponseDTO registerUser(RegisterRequest request) {
        if (usuariosRepository.findByCorreo(request.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        boolean isFirstUser = usuariosRepository.count() == 0;

        Usuarios newUser = new Usuarios();
        newUser.setNombre(request.getUsername()); // Username como nombre base
        newUser.setUsername(request.getUsername());
        newUser.setCorreo(request.getEmail());
        newUser.setContrasena(request.getPassword()); // En un proyecto real esto iría encriptado (Bcrypt)
        newUser.setAuthProvider("LOCAL");
        newUser.setTelefono(0);

        newUser = usuariosRepository.save(newUser);

        if (isFirstUser) {
            Optional<Roles> adminRoleOpt = rolesRepository.findByNombrerol("ADMIN");
            if (adminRoleOpt.isPresent()) {
                UsuarioRol usuarioRol = new UsuarioRol();
                usuarioRol.setUsuario(newUser);
                usuarioRol.setRol(adminRoleOpt.get());
                usuarioRolRepository.save(usuarioRol);
            }
        }

        return usuariosService.convertToDto(newUser);
    }

    public UsuarioResponseDTO loginUser(LoginRequest request) {
        Usuarios usuario = usuariosRepository.findByCorreo(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getContrasena().equals(request.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuariosService.convertToDto(usuario);
    }
}
