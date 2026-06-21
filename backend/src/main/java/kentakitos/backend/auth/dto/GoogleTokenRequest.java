package kentakitos.backend.auth.dto;

import lombok.Data;

@Data
public class GoogleTokenRequest {
    private String accessToken;
}
