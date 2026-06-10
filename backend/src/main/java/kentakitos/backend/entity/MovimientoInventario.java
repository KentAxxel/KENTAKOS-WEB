package kentakitos.backend.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "MovimientoInventario")
@SQLDelete(sql = "UPDATE MovimientoInventario SET deleted = 0 WHERE idmovimientoinventario = ?")
@Where(clause = "deleted = 1")
@Data

public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmovimientoinventario;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;
    
}
