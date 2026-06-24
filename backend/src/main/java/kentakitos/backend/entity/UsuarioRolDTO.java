package kentakitos.backend.entity;
import lombok.Data;

@Data

public class UsuarioRolDTO {
    private Integer idusuario_rol;
    private Integer deleted = 1;
    private Usuarios idusuario;
    private Roles idrol;
}
