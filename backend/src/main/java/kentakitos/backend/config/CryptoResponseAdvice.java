package kentakitos.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kentakitos.backend.dto.CryptoPayload;
import kentakitos.backend.util.CustomCipher;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = {"kentakitos.backend.controller", "kentakitos.backend.auth"})
public class CryptoResponseAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        try {
            // Evitar cifrar dos veces si ya es un CryptoPayload o si es un error
            if (body instanceof CryptoPayload) {
                return body;
            }
            
            String json = objectMapper.writeValueAsString(body);
            String encrypted = CustomCipher.cifrar(json, 5); // Llave 5
            CryptoPayload payload = new CryptoPayload(encrypted);

            // Si el endpoint devolvía un String, Spring usa StringHttpMessageConverter
            // el cual no puede convertir un objeto CryptoPayload a String automáticamente.
            // Así que lo serializamos manualmente a JSON string.
            if (selectedConverterType.getName().contains("StringHttpMessageConverter")) {
                return objectMapper.writeValueAsString(payload);
            }
            
            return payload;
        } catch (Exception e) {
            e.printStackTrace();
            return body;
        }
    }
}
