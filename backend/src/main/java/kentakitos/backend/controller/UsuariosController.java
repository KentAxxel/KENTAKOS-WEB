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

import kentakitos.backend.entity.Roles;
import kentakitos.backend.entity.UsuarioRol;
import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.entity.UsuariosDTO;
import kentakitos.backend.service.IRolesService;
import kentakitos.backend.service.IUsuarioRolService;
import kentakitos.backend.service.IUsuariosService;

@RestController
@RequestMapping("/kentakitos")

public class UsuariosController {
    @Autowired
    private IUsuariosService serviUsuarios;

    @Autowired
    private IRolesService serviRoles;

    @Autowired
    private IUsuarioRolService serviUsuarioRol;

    @GetMapping("/usuarios")
    public List<Usuarios> buscarTodos() {
        return serviUsuarios.buscarTodos();
    }

    @PostMapping("/usuarios")
public ResponseEntity<?> guardar(@RequestBody UsuariosDTO dto) {

    Usuarios usuario = new Usuarios();
    usuario.setNombre(dto.getNombre());
    usuario.setCorreo(dto.getCorreo());
    usuario.setUsername(dto.getUsername());
    usuario.setContrasena(dto.getContrasena());
    usuario.setTelefono(dto.getTelefono());

    // Guardar usuario
    Usuarios nuevo = serviUsuarios.guardar(usuario);

    // Obtener rol INVITADO desde BD (IMPORTANTE)
    Roles rolInvitado = serviRoles.findByNombreRol("INVITADO");

    UsuarioRol usuarioRol = new UsuarioRol();

    // 🔥 AQUÍ ESTÁ LA CORRECCIÓN
    usuarioRol.setIdusuario(nuevo);        // no ID, sino entidad
    usuarioRol.setIdrol(rolInvitado);      // no ID, sino entidad

    serviUsuarioRol.guardar(usuarioRol);

    return ResponseEntity.ok(nuevo);
}

    @PutMapping("/usuarios")
    public ResponseEntity<?> modificar(@RequestBody UsuariosDTO dto) {

        if (dto.getIdusuario() == null) {
            return ResponseEntity.badRequest().body("ID no existe");
        }

        Usuarios usuario = new Usuarios();
        usuario.setIdusuario(dto.getIdusuario());
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setUsername(dto.getUsername());
        usuario.setContrasena(dto.getContrasena());
        usuario.setTelefono(dto.getTelefono());
        return ResponseEntity.ok(serviUsuarios.modificar(usuario));
    }

    @GetMapping("/usuarios/{id}")
    public Optional<Usuarios> buscarPorId(@PathVariable("id") Integer id) {
        return serviUsuarios.buscarPorId(id);
    }

    @DeleteMapping("/usuarios/{id}")
    public String eliminar(@PathVariable Integer id) {
        serviUsuarios.eliminar(id);
        return "Eliminado";
    }
}
