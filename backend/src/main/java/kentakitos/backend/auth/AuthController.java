package kentakitos.backend.auth;

import kentakitos.backend.auth.dto.GoogleTokenRequest;
import kentakitos.backend.entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kentakitos/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UsuariosRepository usuariosRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            // Si llega aquí, la autenticación fue exitosa
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Obtener el usuario desde BD para tener la entidad completa
            Usuarios usuario = usuariosRepository.findByUsername(loginDTO.getUsername())
                    .orElseThrow();

            // Incrementar tokenVersion para invalidar sesiones anteriores
            usuariosRepository.incrementarTokenVersion(usuario.getIdusuario());
            usuario.setTokenVersion(usuario.getTokenVersion() + 1); // mantener consistencia en objeto

            // Generar JWT con la nueva versión
            String jwt = jwtProvider.generarToken(usuario);

            return ResponseEntity.ok(new JwtResponse(jwt, usuario.getNombre(), usuario.getUsername()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
