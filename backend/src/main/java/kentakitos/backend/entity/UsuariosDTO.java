package kentakitos.backend.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@SQLDelete(sql = "UPDATE usuarios SET deleted = 0 WHERE idusuario = ?")
@Where(clause = "deleted = 1")
@Data

public class UsuariosDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;
    private String nombre;
    private String correo;
    private String username;
    private String contrasena;
    private Integer telefono;
    private String authProvider; // "LOCAL" or "GOOGLE"
    private Integer deleted = 1;
}
