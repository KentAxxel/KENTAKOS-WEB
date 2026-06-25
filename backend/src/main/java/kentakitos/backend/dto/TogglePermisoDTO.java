package kentakitos.backend.dto;

import lombok.Data;

@Data
public class TogglePermisoDTO {
    private String rolName;
    private String moduloName;
    private boolean conceder;
}
