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
@Table(name = "mesas")
@SQLDelete(sql = "UPDATE mesas SET deleted = 0 WHERE idmesa = ?")
@Where(clause = "deleted = 1")
@Data

public class Mesas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmesa;
    private Integer numeromesa;
    private Integer capacidad;
    private Integer deleted = 1;

    @Enumerated(EnumType.STRING)
    private EstadoMesa estado;
}
