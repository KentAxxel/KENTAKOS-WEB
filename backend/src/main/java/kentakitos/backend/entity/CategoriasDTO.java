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
@Table(name = "categorias")
@SQLDelete(sql = "UPDATE categorias SET deleted = 0 WHERE id = ?")
@Where(clause = "deleted = 1")
@Data

public class CategoriasDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idcategoria;
    private String nombrecategoria;
    private Integer deleted = 1;
}
