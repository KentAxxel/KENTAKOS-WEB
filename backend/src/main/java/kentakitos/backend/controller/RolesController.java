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
import kentakitos.backend.entity.RolesDTO;
import kentakitos.backend.service.IRolesService;

@RestController
@RequestMapping("/kentakitos")

public class RolesController {
    @Autowired
    private IRolesService serviRoles;

    @GetMapping("/roles")
    public List<Roles> buscarTodos() {
        return serviRoles.buscarTodos();
    }

    @PostMapping("/roles")
    public ResponseEntity<?> guardar(@RequestBody RolesDTO dto) {
        Roles rol = new Roles();
        rol.setNombrerol(dto.getNombrerol());
        return ResponseEntity.ok(serviRoles.guardar(rol));
    }

    @PutMapping("/roles")
    public ResponseEntity<?> modificar(@RequestBody RolesDTO dto) {

        if (dto.getIdrol() == null) {
            return ResponseEntity.badRequest().body("ID no existe");
        }

        Roles rol = new Roles();
        rol.setIdrol(dto.getIdrol());
        rol.setNombrerol(dto.getNombrerol());

        return ResponseEntity.ok(serviRoles.modificar(rol));
    }

    @GetMapping("/roles/{id}")
    public Optional<Roles> buscarPorId(@PathVariable("id") Integer id) {
        return serviRoles.buscarPorId(id);
    }

    @DeleteMapping("/roles/{id}")
    public String eliminar(@PathVariable Integer id) {
        serviRoles.eliminar(id);
        return "Eliminado";
    }
}
