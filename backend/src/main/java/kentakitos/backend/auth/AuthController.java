package kentakitos.backend.auth;

import kentakitos.backend.auth.dto.GoogleTokenRequest;
import kentakitos.backend.entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private GoogleAuthService googleAuthService;

    @PostMapping("/google")
    public ResponseEntity<?> authenticateWithGoogle(@RequestBody GoogleTokenRequest request) {
        try {
            Usuarios usuario = googleAuthService.verifyAndSaveUser(request.getAccessToken());
            // En el futuro, aquí generaríamos y devolveríamos nuestro propio JWT.
            // Por ahora, solo retornamos los datos del usuario.
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al autenticar: " + e.getMessage());
        }
    }
}
