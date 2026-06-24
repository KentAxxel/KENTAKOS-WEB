package kentakitos.backend.entity;

import lombok.Data;

@Data

public class MesasDTO {
    private Integer idmesa;
    private Integer numeromesa;
    private Integer capacidad;
    private EstadoMesa estado;
    private Integer deleted = 1;
}
