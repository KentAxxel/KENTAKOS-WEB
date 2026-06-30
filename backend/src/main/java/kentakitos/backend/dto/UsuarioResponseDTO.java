package kentakitos.backend.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Integer id;
    private String encryptedId; // ID ofuscado para seguridad en tránsito
    private String name;
    private String email;
    private String role;
    private Integer roleId;
    private String status;
    private String avatar;
    private String authProvider;
    private String username;
    private String sessionToken;
}
