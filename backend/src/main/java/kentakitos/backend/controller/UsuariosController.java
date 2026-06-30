package kentakitos.backend.controller;

import kentakitos.backend.dto.UsuarioResponseDTO;
import kentakitos.backend.service.UsuariosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios() {
        return ResponseEntity.ok(usuariosService.getAllUsuarios());
    }

    @org.springframework.web.bind.annotation.PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(
            @org.springframework.web.bind.annotation.RequestBody kentakitos.backend.auth.dto.RegisterRequest request) {
        return ResponseEntity.ok(usuariosService.createUsuario(request));
    }

    @org.springframework.web.bind.annotation.PutMapping("/{encryptedId}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(
            @org.springframework.web.bind.annotation.PathVariable String encryptedId,
            @org.springframework.web.bind.annotation.RequestBody kentakitos.backend.dto.UsuarioUpdateDTO dto) {
        // Descifrar el ID recibido
        String decryptedStr = kentakitos.backend.util.CustomCipher.descifrar(encryptedId, 5);
        Integer id = Integer.parseInt(decryptedStr);
        return ResponseEntity.ok(usuariosService.updateUsuario(id, dto));
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{encryptedId}")
    public ResponseEntity<Void> deleteUsuario(
            @org.springframework.web.bind.annotation.PathVariable String encryptedId) {
        String decryptedStr = kentakitos.backend.util.CustomCipher.descifrar(encryptedId, 5);
        Integer id = Integer.parseInt(decryptedStr);
        usuariosService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
