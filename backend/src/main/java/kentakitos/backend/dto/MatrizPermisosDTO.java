package kentakitos.backend.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class MatrizPermisosDTO {
    private List<String> modulos;
    private List<String> roles;
    // Mapeo: modulo -> (rol -> tiene_permiso)
    // Ejemplo: "VER_CAJA" -> {"ADMIN": true, "USUARIO": true}
    private Map<String, Map<String, Boolean>> matriz;
}
