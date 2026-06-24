package kentakitos.backend.entity;

import lombok.Data;

@Data

public class DetalleVentaDTO {
    private Integer iddetalleventa;
    private Integer cantidad;
    private Double preciounitario;  
    private Double subtotal;
    private Integer idproducto;
    private Integer idventa;
    private Integer deleted = 1;
}
