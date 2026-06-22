package kentakitos.backend.entity;

import java.time.LocalDate;

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
@Table(name = "ventas")
@SQLDelete(sql = "UPDATE ventas SET deleted = 0 WHERE idventa = ?")
@Where(clause = "deleted = 1")
@Data

public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idventa;
    private LocalDate fecha;
    private Integer deleted = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcliente")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Clientes cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpedido")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pedidos pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmesa")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Mesas mesa;

    public Ventas(){}
    public Ventas(Integer id){
        this.idventa = id;
    }
}
