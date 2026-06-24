package kentakitos.backend.entity;
import lombok.Data;

@Data

public class ProductosDTO {
    private Integer idproducto;
    private String nombreproducto;
    private Double precio;
    private String imagen;
    private Integer idcategoria;
    private Integer deleted = 1;
}
