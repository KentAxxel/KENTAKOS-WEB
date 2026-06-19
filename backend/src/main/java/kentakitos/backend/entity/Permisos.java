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
@Table(name = "Permisos")
@SQLDelete(sql = "UPDATE Permisos SET deleted = 0 WHERE idpermiso = ?")
@Where(clause = "deleted = 1")
@Data

public class Permisos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpermiso;
    private String nombrepermiso;
    private Integer deleted;

    public Permisos() {}
    public Permisos(Integer id){
        this.idpermiso = id;
    }

}
