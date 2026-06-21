package kentakitos.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class RolResponseDTO {
    private Integer idRol;
    private String nombre;
    private String descripcion;
    private Integer estado;
    private List<PermisoResponseDTO> permisos;
}
