package kentakitos.backend.controller;

import kentakitos.backend.dto.RolResponseDTO;
import kentakitos.backend.service.RolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/roles")
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public ResponseEntity<List<RolResponseDTO>> getAllRoles() {
        return ResponseEntity.ok(rolesService.getAllRoles());
    }

    @org.springframework.web.bind.annotation.PostMapping
    public ResponseEntity<RolResponseDTO> createRole(
            @org.springframework.web.bind.annotation.RequestBody kentakitos.backend.dto.RolUpdateDTO dto) {
        return ResponseEntity.ok(rolesService.createRole(dto));
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public ResponseEntity<RolResponseDTO> updateRole(
            @org.springframework.web.bind.annotation.PathVariable Integer id,
            @org.springframework.web.bind.annotation.RequestBody kentakitos.backend.dto.RolUpdateDTO dto) {
        return ResponseEntity.ok(rolesService.updateRole(id, dto));
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(
            @org.springframework.web.bind.annotation.PathVariable Integer id) {
        rolesService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
