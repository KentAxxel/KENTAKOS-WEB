package kentakitos.backend.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kentakitos.backend.entity.Clientes;
import kentakitos.backend.repository.ClientesRepository;
import kentakitos.backend.service.IClientesService;

@Service

public class ClientesService implements IClientesService {
    @Autowired
    private ClientesRepository repoClientes;

    public List<Clientes> buscarTodos(){
        return repoClientes.findAll();
    }
    public Clientes guardar(Clientes cliente){
        return repoClientes.save(cliente);
    }
    public Clientes modificar(Clientes cliente){
        return repoClientes.save(cliente);
    }
    public Optional<Clientes> buscarPorId(Integer id){
        return repoClientes.findById(id);
    }
    public void eliminar(Integer id){
        repoClientes.deleteById(id);
    }
}
