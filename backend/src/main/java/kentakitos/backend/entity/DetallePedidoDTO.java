package kentakitos.backend.entity;

import lombok.Data;

@Data

public class DetallePedidoDTO {
    private Integer iddetallepedido;
    private Integer cantidad;
    private Double precio;
    private Integer idproducto;
    private Integer idpedido;
    private Integer deleted = 1;

}
