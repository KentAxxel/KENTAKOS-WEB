package kentakitos.backend.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kentakitos.backend.util.CustomCipher;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@ControllerAdvice(basePackages = {"kentakitos.backend.controller", "kentakitos.backend.auth"})
public class CryptoRequestAdvice extends RequestBodyAdviceAdapter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        // Leemos el cuerpo original entrante
        byte[] bodyBytes = inputMessage.getBody().readAllBytes();
        if (bodyBytes.length == 0) {
            return inputMessage;
        }

        String rawJson = new String(bodyBytes, StandardCharsets.UTF_8);
        try {
            JsonNode rootNode = objectMapper.readTree(rawJson);
            
            // Verificamos si es nuestra estructura cifrada { "payload": "..." }
            if (rootNode.has("payload")) {
                String encryptedPayload = rootNode.get("payload").asText();
                String decryptedJson = CustomCipher.descifrar(encryptedPayload, 5); // Llave 5
                
                return new HttpInputMessage() {
                    @Override
                    public InputStream getBody() throws IOException {
                        return new ByteArrayInputStream(decryptedJson.getBytes(StandardCharsets.UTF_8));
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        return inputMessage.getHeaders();
                    }
                };
            }
        } catch (Exception e) {
            // No era JSON válido o falló el descifrado, continuar normal
        }

        // Si no tiene payload, devolvemos el body original
        return new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(bodyBytes);
            }

            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
        };
    }
}
