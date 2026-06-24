package kentakitos.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kentakitos.backend.entity.Clientes;
import kentakitos.backend.entity.ClientesDTO;
import kentakitos.backend.service.IClientesService;

@RestController
@RequestMapping("/kentakitos")

public class ClientesController {

    @Autowired
    private IClientesService serviClientes;

    @GetMapping("/clientes")
    public List<Clientes> buscarTodos() {
        return serviClientes.buscarTodos();
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> guardar(@RequestBody ClientesDTO dto) {
        Clientes cliente = new Clientes();
        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());
        return ResponseEntity.ok(serviClientes.guardar(cliente));
    }

    @PutMapping("/clientes")
    public ResponseEntity<?> modificar(@RequestBody ClientesDTO dto) {

        if (dto.getIdcliente() == null) {
            return ResponseEntity.badRequest().body("ID no existe");
        }

        Clientes cliente = new Clientes();
        cliente.setIdcliente(dto.getIdcliente());
        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());

        return ResponseEntity.ok(serviClientes.modificar(cliente));
    }

    @GetMapping("/clientes/{id}")
    public Optional<Clientes> buscarPorId(@PathVariable("id") Integer id) {
        return serviClientes.buscarPorId(id);
    }

    @DeleteMapping("/clientes/{id}")
    public String eliminar(@PathVariable Integer id) {
        serviClientes.eliminar(id);
        return "Eliminado";
    }
}
