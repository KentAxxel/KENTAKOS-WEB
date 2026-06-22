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
@Table(name = "ventas")
@SQLDelete(sql = "UPDATE ventas SET deleted = 0 WHERE idventa = ?")
@Where(clause = "deleted = 1")
@Data

public class VentasDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idventa;
    private LocalDate fecha;
    private Integer idcliente;
    private Integer idpedido;
    private Integer idmesa;
    private Integer deleted = 1;

}
