package kentakitos.backend.entity;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pedidos")
@SQLDelete(sql = "UPDATE pedidos SET deleted = 0 WHERE idpedido = ?")
@Where(clause = "deleted = 1")
@Data

public class PedidosDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpedido;
    private LocalDate fecha;
    private String observaciones;
    private Integer idcliente;
    private Integer idmesa;
    private String estado;
    private Integer deleted;
}
