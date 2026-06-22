package kentakitos.backend.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "DetallePedido")
@SQLDelete(sql = "UPDATE DetallePedido SET deleted = 0 WHERE iddetallepedido = ?")
@Where(clause = "deleted = 1")
@Data

public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddetallepedido;
    private Integer cantidad;
    private Double precio;
    private Integer deleted = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproducto")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Productos producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpedido")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pedidos pedido;

    public DetallePedido() {}
    public DetallePedido(Integer id) {
        this.iddetallepedido = id;
    }
}
