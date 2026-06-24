package kentakitos.backend.entity;

import lombok.Data;

@Data

public class RolesDTO {
    private Integer idrol;
    private String nombrerol;
    private Integer deleted = 1;
}
