package kentakitos.backend.entity;

import java.time.LocalDate;
import lombok.Data;

@Data

public class PedidosDTO {
    private Integer idpedido;
    private LocalDate fecha;
    private String observaciones;
    private Integer idcliente;
    private Integer idmesa;
    private String estado;
    private Integer deleted;
}
