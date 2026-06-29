package kentakitos.backend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    private String username;   // puede ser username o email, lo decide el front
    @NotBlank
    private String password;
}