package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kentakitos.backend.entity.UsuarioRol;
import kentakitos.backend.repository.UsuarioRolRepository;
import kentakitos.backend.service.IUsuarioRolService;

@Service

public class UsuarioRolService implements IUsuarioRolService {
    @Autowired
    private UsuarioRolRepository repoCategoria;

    public List<UsuarioRol> buscarTodos(){
        return repoCategoria.findAll();
    }
    public UsuarioRol guardar(UsuarioRol usuarioRol){
        return repoCategoria.save(usuarioRol);
    }
    public UsuarioRol modificar(UsuarioRol usuarioRol){
        return repoCategoria.save(usuarioRol);
    }
    public Optional<UsuarioRol> buscarPorId(Integer id){
        return repoCategoria.findById(id);
    }
    public void eliminar(Integer id){
        repoCategoria.deleteById(id);
    }
}
