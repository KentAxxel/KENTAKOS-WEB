package kentakitos.backend.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityHeadersFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Prevención de XSS
        httpResponse.setHeader("X-XSS-Protection", "1; mode=block");
        
        // Prevenir Clickjacking
        httpResponse.setHeader("X-Frame-Options", "DENY");
        
        // Evitar que el navegador adivine el tipo de contenido (MIME-sniffing)
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");
        
        // Content Security Policy básica
        httpResponse.setHeader("Content-Security-Policy", "default-src 'self' http://localhost:5173; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline' https://fonts.googleapis.com; font-src 'self' https://fonts.gstatic.com;");
        
        // HSTS (HTTP Strict Transport Security) - Obligar HTTPS
        httpResponse.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización vacía
    }

    @Override
    public void destroy() {
        // Destrucción vacía
    }
}
