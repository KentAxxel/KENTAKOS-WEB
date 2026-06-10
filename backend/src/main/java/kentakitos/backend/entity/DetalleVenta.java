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
@Table(name = "DetalleVenta")
@SQLDelete(sql = "UPDATE DetalleVenta SET deleted = 0 WHERE iddetalleventa = ?")
@Where(clause = "deleted = 1")
@Data

public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddetalleventa;
    private Integer cantidad;
    private Double preciounitario;  
    private Double subtotal;
    private Integer deleted = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproducto")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Productos producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idventa")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Ventas venta;

    public DetalleVenta() {}
    public DetalleVenta(Integer id) {
        this.iddetalleventa = id;
    }
}
