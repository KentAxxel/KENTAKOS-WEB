package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kentakitos.backend.entity.Permisos;
import kentakitos.backend.repository.PermisosRepository;
import kentakitos.backend.service.IPermisosService;

@Service

public class PermisosService implements IPermisosService {
    @Autowired
    private PermisosRepository repoPermisos;

    public List<Permisos> buscarTodos(){
        return repoPermisos.findAll();
    }
    public Permisos guardar(Permisos permiso){
        return repoPermisos.save(permiso);
    }
    public Permisos modificar(Permisos permiso){
        return repoPermisos.save(permiso);
    }
    public Optional<Permisos> buscarPorId(Integer id){
        return repoPermisos.findById(id);
    }
    public void eliminar(Integer id){
        repoPermisos.deleteById(id);
    }
}
