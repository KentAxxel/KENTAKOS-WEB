package kentakitos.backend.controller;

import kentakitos.backend.dto.MatrizPermisosDTO;
import kentakitos.backend.service.PermisosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/permissions")
public class PermisosController {

    private final PermisosService permisosService;

    public PermisosController(PermisosService permisosService) {
        this.permisosService = permisosService;
    }

    @GetMapping
    public ResponseEntity<MatrizPermisosDTO> getMatrizPermisos() {
        return ResponseEntity.ok(permisosService.getMatrizPermisos());
    }
}
