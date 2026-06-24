package kentakitos.backend.entity;

import java.time.LocalDate;
import lombok.Data;

@Data

public class VentasDTO {
    private Integer idventa;
    private LocalDate fecha;
    private Integer idcliente;
    private Integer idpedido;
    private Integer idmesa;
    private Integer deleted = 1;

}
