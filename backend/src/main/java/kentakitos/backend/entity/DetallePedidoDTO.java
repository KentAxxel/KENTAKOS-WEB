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
@Table(name = "DetallePedido")
@SQLDelete(sql = "UPDATE DetallePedido SET deleted = 0 WHERE iddetallepedido = ?")
@Where(clause = "deleted = 1")
@Data

public class DetallePedidoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddetallepedido;
    private Integer cantidad;
    private Double precio;
    private Integer idproducto;
    private Integer idpedido;
    private Integer deleted = 1;

}
