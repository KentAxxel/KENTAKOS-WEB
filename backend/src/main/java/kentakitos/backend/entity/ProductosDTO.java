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
@Table(name = "productos")
@SQLDelete(sql = "UPDATE productos SET deleted = 0 WHERE idproducto = ?")
@Where(clause = "deleted = 1")
@Data

public class ProductosDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproducto;
    private String nombreproducto;
    private Double precio;
    private String imagen;
    private Categorias idcategoria;
    private Integer deleted = 1;
}
