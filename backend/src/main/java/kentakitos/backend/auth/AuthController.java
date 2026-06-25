package kentakitos.backend.auth;

import kentakitos.backend.auth.dto.GoogleTokenRequest;
import kentakitos.backend.auth.dto.LoginRequest;
import kentakitos.backend.auth.dto.RegisterRequest;
import kentakitos.backend.dto.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private GoogleAuthService googleAuthService;

    @Autowired
    private AuthService authService;

    @PostMapping("/google")
    public ResponseEntity<?> authenticateWithGoogle(@RequestBody GoogleTokenRequest request) {
        try {
            UsuarioResponseDTO usuario = googleAuthService.verifyAndSaveUser(request.getAccessToken());
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al autenticar: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            UsuarioResponseDTO usuario = authService.registerUser(request);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        try {
            UsuarioResponseDTO usuario = authService.loginUser(request);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al iniciar sesión: " + e.getMessage());
        }
    }
}
