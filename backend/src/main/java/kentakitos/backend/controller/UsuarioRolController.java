package kentakitos.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kentakitos.backend.entity.UsuarioRol;
import kentakitos.backend.entity.UsuarioRolDTO;
import kentakitos.backend.service.IUsuarioRolService;

@RestController
@RequestMapping("/kentakitos")

public class UsuarioRolController {
    @Autowired
    private IUsuarioRolService serviUsuarioRol;

    @GetMapping("/usuariorol")
    public List<UsuarioRol> buscarTodos() {
        return serviUsuarioRol.buscarTodos();
    }

    @PostMapping("/usuariorol")
    public ResponseEntity<?> guardar(@RequestBody UsuarioRolDTO dto) {
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setIdusuario(dto.getIdusuario());
        usuarioRol.setIdrol(dto.getIdrol());
        return ResponseEntity.ok(serviUsuarioRol.guardar(usuarioRol));
    }

    @PutMapping("/usuariorol")
    public ResponseEntity<?> modificar(@RequestBody UsuarioRolDTO dto) {

        if (dto.getIdusuario_rol() == null) {
            return ResponseEntity.badRequest().body("ID no existe");
        }

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setIdusuario_rol(dto.getIdusuario_rol());
        usuarioRol.setIdusuario(dto.getIdusuario());
        usuarioRol.setIdrol(dto.getIdrol());

        return ResponseEntity.ok(serviUsuarioRol.modificar(usuarioRol));
    }

    @GetMapping("/usuariorol/{id}")
    public Optional<UsuarioRol> buscarPorId(@PathVariable("id") Integer id) {
        return serviUsuarioRol.buscarPorId(id);
    }

    @DeleteMapping("/usuariorol/{id}")
    public String eliminar(@PathVariable Integer id) {
        serviUsuarioRol.eliminar(id);
        return "Eliminado";
    }
}
