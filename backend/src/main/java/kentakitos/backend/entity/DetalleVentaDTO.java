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
@Table(name = "DetalleVenta")
@SQLDelete(sql = "UPDATE DetalleVenta SET deleted = 0 WHERE iddetalleventa = ?")
@Where(clause = "deleted = 1")
@Data

public class DetalleVentaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddetalleventa;
    private Integer cantidad;
    private Double preciounitario;  
    private Double subtotal;
    private Integer idproducto;
    private Integer idventa;
    private Integer deleted = 1;
}
