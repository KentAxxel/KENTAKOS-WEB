package kentakitos.backend.auth;

import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
public class GoogleAuthService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public Usuarios verifyAndSaveUser(String accessToken) {
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
            Optional<Usuarios> existingUser = usuariosRepository.findByCorreo(email);

            if (existingUser.isPresent()) {
                // Si existe, lo retornamos (Login exitoso)
                return existingUser.get();
            } else {
                // Si no existe, lo creamos (Registro exitoso)
                Usuarios newUser = new Usuarios();
                newUser.setCorreo(email);
                newUser.setNombre(name);
                newUser.setUsername(email.split("@")[0]); // username sugerido
                newUser.setAuthProvider("GOOGLE");
                // Como la base de datos exige que estos campos no sean nulos,
                // les ponemos un valor por defecto para usuarios de Google
                newUser.setContrasena("GOOGLE_AUTH"); 
                newUser.setTelefono(0);
                
                return usuariosRepository.save(newUser);
            }

        } catch (Exception e) {
            throw new RuntimeException("Token de Google inválido o expirado", e);
        }
    }
}
