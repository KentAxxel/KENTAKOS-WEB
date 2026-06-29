package kentakitos.backend.config;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.google.api.client.util.Value;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import kentakitos.backend.entity.Usuarios;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    public String generarToken(Usuarios usuario) {
        List<String> roles = usuario.getUsuarioRoles().stream()
                .map(ur -> ur.getIdrol().getNombrerol())
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(usuario.getUsername())               // o correo
                .claim("id", usuario.getIdusuario())
                .claim("roles", roles)
                .claim("tokenVersion", usuario.getTokenVersion())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public Integer getTokenVersionFromToken(String token) {
        return (Integer) Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build()
                .parseClaimsJws(token).getBody().get("tokenVersion");
    }
}