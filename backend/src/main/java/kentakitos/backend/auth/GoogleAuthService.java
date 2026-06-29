package kentakitos.backend.auth;

import kentakitos.backend.dto.UsuarioResponseDTO;
import kentakitos.backend.entity.Roles;
import kentakitos.backend.entity.UsuarioRol;
import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.repository.RolesRepository;
import kentakitos.backend.repository.UsuarioRolRepository;
import kentakitos.backend.repository.UsuariosRepository;
import kentakitos.backend.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Map;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import kentakitos.backend.util.PasswordUtils;

@Service
public class GoogleAuthService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Autowired
    private UsuariosService usuariosService;

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
    public UsuarioResponseDTO verifyAndSaveUser(String accessToken) {
        // 1. Verificar el token con Google y obtener la información del usuario
        RestTemplate restTemplate = new RestTemplate();
        String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, Map.class);
            Map<String, Object> payload = response.getBody();

            if (payload == null || !payload.containsKey("email")) {
                throw new RuntimeException("No se pudo obtener el email de Google");
            }

            String email = (String) payload.get("email");
            String name = (String) payload.get("name");

            // 2. Buscar si el usuario ya existe
            Optional<Usuarios> existingUserOpt = usuariosRepository.findByCorreo(email);

            if (existingUserOpt.isPresent()) {
                Usuarios existingUser = existingUserOpt.get();
                // Si existe, comprobamos la sesión
                checkActiveSession(existingUser);
                startNewSession(existingUser);
                existingUser = usuariosRepository.save(existingUser);
                return usuariosService.convertToDto(existingUser);
            } else {
                boolean isFirstUser = usuariosRepository.count() == 0;

                // Si no existe, lo creamos (Registro exitoso)
                Usuarios newUser = new Usuarios();
                newUser.setCorreo(email);
                newUser.setNombre(name);
                newUser.setUsername(email.split("@")[0]); // username sugerido
                newUser.setAuthProvider("GOOGLE");
                // Como la base de datos exige que estos campos no sean nulos,
                // les ponemos un valor por defecto para usuarios de Google, ahora encriptado
                newUser.setContrasena(PasswordUtils.hashSHA256("GOOGLE_AUTH")); 
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

        } catch (Exception e) {
            throw new RuntimeException("Token de Google inválido o expirado", e);
        }
    }
}
