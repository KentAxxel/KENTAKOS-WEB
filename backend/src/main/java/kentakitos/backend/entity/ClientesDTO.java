package kentakitos.backend.entity;

import lombok.Data;

@Data

public class ClientesDTO {
    private Integer idcliente;
    private String nombre;
    private String correo;
    private Integer deleted = 1;
}
