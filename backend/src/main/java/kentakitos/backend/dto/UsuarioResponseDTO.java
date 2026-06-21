package kentakitos.backend.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String role;
    private String status;
    private String avatar;
}
