package kentakitos.backend.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@Table(name = "rol_permiso")
@SQLDelete(sql = "UPDATE rol_permiso SET deleted= 1 WHERE idrol_permiso = ?")
@Where(clause = "deleted = 1")
@Data

public class RolPermiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrol_permiso;
    private Integer deleted = 1;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrol")
    private Roles idrol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpermiso")
    private Permisos permiso;
}
