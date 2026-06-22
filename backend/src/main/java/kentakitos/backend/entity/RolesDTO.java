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
@Table(name = "roles")
@SQLDelete(sql = "UPDATE roles SET deleted = 0 WHERE idrol = ?")
@Where(clause = "deleted = 1")
@Data

public class RolesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrol;
    private String nombrerol;
    private Integer deleted = 1;
}
