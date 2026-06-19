package kentakitos.backend.entity;

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
@Data

public class RolPermiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrol")
    private Roles rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpermiso")
    private Permisos permiso;
}
