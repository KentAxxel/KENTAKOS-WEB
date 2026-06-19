package kentakitos.backend.entity;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Pedidos")
@SQLDelete(sql = "UPDATE Pedidos SET deleted = 0 WHERE idpedido = ?")
@Where(clause = "deleted = 1")
@Data

public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpedido;
    private LocalDate fecha;
    private String observaciones;
    private Integer deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcliente")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Clientes cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmesa")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Mesas mesa;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
}
