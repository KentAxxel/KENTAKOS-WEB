package kentakitos.backend.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuario_rol")
@SQLDelete(sql = "UPDATE usuario_rol SET deleted = 0 WHERE idusuario_rol = ?")
@Where(clause = "deleted = 1")
@Data

public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario_rol;
    private Integer deleted = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrol")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Roles rol;
}
