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

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Optional;

import kentakitos.backend.util.PasswordUtils;

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

    private void checkActiveSession(Usuarios usuario) {
        if (usuario.getSessionToken() != null && usuario.getLastActive() != null) {
            LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);
            if (usuario.getLastActive().isAfter(fiveMinutesAgo)) {
                throw new RuntimeException("Ya tienes una sesión activa en otro dispositivo");
            }
        }
    }

    private void startNewSession(Usuarios usuario) {
        usuario.setSessionToken(UUID.randomUUID().toString());
        usuario.setLastActive(LocalDateTime.now());
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
        newUser.setContrasena(PasswordUtils.hashSHA256(request.getPassword()));
        newUser.setAuthProvider("LOCAL");
        newUser.setTelefono(0);
        
        startNewSession(newUser);

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

    @Transactional
    public UsuarioResponseDTO loginUser(LoginRequest request) {
        Usuarios usuario = usuariosRepository.findByCorreo(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String hashedInputPassword = PasswordUtils.hashSHA256(request.getPassword());
        if (!usuario.getContrasena().equals(hashedInputPassword)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        checkActiveSession(usuario);
        startNewSession(usuario);
        
        usuario = usuariosRepository.save(usuario);

        return usuariosService.convertToDto(usuario);
    }

    @Transactional
    public void heartbeat(Integer userId, String sessionToken) {
        Usuarios usuario = usuariosRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (sessionToken != null && sessionToken.equals(usuario.getSessionToken())) {
            usuario.setLastActive(LocalDateTime.now());
            usuariosRepository.save(usuario);
        } else {
            throw new RuntimeException("Sesión inválida o expirada");
        }
    }

    @Transactional
    public void logout(Integer userId) {
        Usuarios usuario = usuariosRepository.findById(userId).orElse(null);
        if (usuario != null) {
            usuario.setSessionToken(null);
            usuario.setLastActive(null);
            usuariosRepository.save(usuario);
        }
    }
}
