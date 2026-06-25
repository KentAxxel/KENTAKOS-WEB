package kentakitos.backend.dto;

import lombok.Data;

@Data
public class UsuarioUpdateDTO {
    private String nombre;
    private String username;
    private String correo;
    private String contrasena; // Optional, only updated if not empty and if authProvider is LOCAL
    private Integer rolId;
}
