package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.repository.UsuariosRepository;
import kentakitos.backend.service.IUsuariosService;

@Service

public class UsuariosService implements IUsuariosService {
    @Autowired
    private UsuariosRepository repoUsuarios;

    public List<Usuarios> buscarTodos(){
        return repoUsuarios.findAll();
    }
    public Usuarios guardar(Usuarios usuario){
        return repoUsuarios.save(usuario);
    }
    public Usuarios modificar(Usuarios usuario){
        return repoUsuarios.save(usuario);
    }
    public Optional<Usuarios> buscarPorId(Integer id){
        return repoUsuarios.findById(id);
    }
    public void eliminar(Integer id){
        repoUsuarios.deleteById(id);
    }
}
