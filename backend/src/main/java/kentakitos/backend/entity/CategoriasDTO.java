package kentakitos.backend.entity;

import lombok.Data;

@Data

public class CategoriasDTO {
    private Integer idcategoria;
    private String nombrecategoria;
    private Integer deleted = 1;
}
