package kentakitos.backend.entity;
import lombok.Data;

@Data

public class UsuariosDTO {
    private Integer idusuario;
    private String nombre;
    private String correo;
    private String username;
    private String contrasena;
    private Integer telefono;
    private String authProvider; // "LOCAL" or "GOOGLE"
    private Integer deleted = 1;
}
