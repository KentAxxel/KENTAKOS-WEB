package kentakitos.backend.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data

public class InventarioDTO {
    private Integer idinventario;
    private String nombreinsumo;
    private Integer cantidad;
    private Integer cantidadmin;
    private LocalDateTime fechaactualizacion;
    private Integer deleted = 1;
}
