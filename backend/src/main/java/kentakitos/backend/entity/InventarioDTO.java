package kentakitos.backend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inventario")
@SQLDelete(sql = "UPDATE inventario SET deleted = 0 WHERE idinventario = ?")
@Where(clause = "deleted = 1")
@Data

public class InventarioDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idinventario;
    private String nombreinsumo;
    private Integer cantidad;
    private Integer cantidadmin;
    private LocalDateTime fechaactualizacion;
    private Integer deleted = 1;
}
