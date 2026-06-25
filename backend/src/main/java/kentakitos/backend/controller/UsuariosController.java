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

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(
            @org.springframework.web.bind.annotation.PathVariable Integer id,
            @org.springframework.web.bind.annotation.RequestBody kentakitos.backend.dto.UsuarioUpdateDTO dto) {
        return ResponseEntity.ok(usuariosService.updateUsuario(id, dto));
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(
            @org.springframework.web.bind.annotation.PathVariable Integer id) {
        usuariosService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
