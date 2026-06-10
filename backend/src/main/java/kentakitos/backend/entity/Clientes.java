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
@Table(name = "Clientes")
@SQLDelete(sql = "UPDATE Clientes SET deleted = 0 WHERE idcliente = ?")
@Where(clause = "estado = 1")
@Data

public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcliente;
    private String nombre;
    private String correo;
    private Integer deleted = 1;
    
    public Clientes (){}
    public Clientes (Integer id){
        this.idcliente = id;
    }
}
