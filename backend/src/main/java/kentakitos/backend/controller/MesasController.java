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

import kentakitos.backend.entity.Mesas;
import kentakitos.backend.entity.MesasDTO;
import kentakitos.backend.service.IMesasService;

@RestController
@RequestMapping("/kentakitos")

public class MesasController {
    @Autowired
    private IMesasService serviMesas;

    @GetMapping("/mesas")
    public List<Mesas> buscarTodos() {
        return serviMesas.buscarTodos();
    }

    @PostMapping("/mesas")
    public ResponseEntity<?> guardar(@RequestBody MesasDTO dto) {
        Mesas mesas = new Mesas();
        mesas.setNumeromesa(dto.getNumeromesa());
        mesas.setCapacidad(dto.getCapacidad());
        mesas.setEstado(dto.getEstado());
        return ResponseEntity.ok(serviMesas.guardar(mesas));
    }

    @PutMapping("/mesas")
    public ResponseEntity<?> modificar(@RequestBody MesasDTO dto) {

        if (dto.getIdmesa() == null) {
            return ResponseEntity.badRequest().body("ID no existe");
        }

        Mesas mesas = new Mesas();
        mesas.setIdmesa(dto.getIdmesa());
        mesas.setNumeromesa(dto.getNumeromesa());
        mesas.setCapacidad(dto.getCapacidad());
        mesas.setEstado(dto.getEstado());

        return ResponseEntity.ok(serviMesas.modificar(mesas));
    }

    @GetMapping("/mesas/{id}")
    public Optional<Mesas> buscarPorId(@PathVariable("id") Integer id) {
        return serviMesas.buscarPorId(id);
    }

    @DeleteMapping("/mesas/{id}")
    public String eliminar(@PathVariable Integer id) {
        serviMesas.eliminar(id);
        return "Eliminado";
    }
}
