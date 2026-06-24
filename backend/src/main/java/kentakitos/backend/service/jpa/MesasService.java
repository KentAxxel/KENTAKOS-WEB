package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kentakitos.backend.entity.Mesas;
import kentakitos.backend.repository.MesasRepository;
import kentakitos.backend.service.IMesasService;

@Service

public class MesasService implements IMesasService {
    @Autowired
    private MesasRepository repoMesas;

    public List<Mesas> buscarTodos(){
        return repoMesas.findAll();
    }
    public Mesas guardar(Mesas mesa){
        return repoMesas.save(mesa);
    }
    public Mesas modificar(Mesas mesa){
        return repoMesas.save(mesa);
    }
    public Optional<Mesas> buscarPorId(Integer id){
        return repoMesas.findById(id);
    }
    public void eliminar(Integer id){
        repoMesas.deleteById(id);
    }
}
