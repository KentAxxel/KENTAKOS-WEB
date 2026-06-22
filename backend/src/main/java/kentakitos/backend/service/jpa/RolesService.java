package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import kentakitos.backend.entity.Roles;
import kentakitos.backend.repository.RolesRepository;

public class RolesService {
    @Autowired
    private RolesRepository repoRoles;

    public List<Roles> buscarTodos(){
        return repoRoles.findAll();
    }
    public Roles guardar(Roles rol){
        return repoRoles.save(rol);
    }
    public Roles modificar(Roles rol){
        return repoRoles.save(rol);
    }
    public Optional<Roles> buscarPorId(Integer id){
        return repoRoles.findById(id);
    }
    public void eliminar(Integer id){
        repoRoles.deleteById(id);
    }
}
