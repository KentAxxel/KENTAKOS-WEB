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

import kentakitos.backend.entity.Inventario;
import kentakitos.backend.entity.InventarioDTO;
import kentakitos.backend.service.IInventarioService;

@RestController
@RequestMapping("/kentakitos")

public class InventarioController {
    @Autowired
    private IInventarioService serviInventario;

    @GetMapping("/inventario")
    public List<Inventario> buscarTodos() {
        return serviInventario.buscarTodos();
    }

    @PostMapping("/inventario")
    public ResponseEntity<?> guardar(@RequestBody InventarioDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setNombreinsumo(dto.getNombreinsumo());
        inventario.setCantidad(dto.getCantidad());
        inventario.setCantidadmin(dto.getCantidadmin());
        inventario.setFechaactualizacion(dto.getFechaactualizacion());
        return ResponseEntity.ok(serviInventario.guardar(inventario));
    }

    @PutMapping("/inventario")
    public ResponseEntity<?> modificar(@RequestBody InventarioDTO dto) {

        if (dto.getIdinventario() == null) {
            return ResponseEntity.badRequest().body("ID no existe");
        }

        Inventario inventario = new Inventario();
        inventario.setIdinventario(dto.getIdinventario());
        inventario.setNombreinsumo(dto.getNombreinsumo());
        inventario.setCantidad(dto.getCantidad());
        inventario.setCantidadmin(dto.getCantidadmin());
        inventario.setFechaactualizacion(dto.getFechaactualizacion());

        return ResponseEntity.ok(serviInventario.modificar(inventario));
    }

    @GetMapping("/inventario/{id}")
    public Optional<Inventario> buscarPorId(@PathVariable("id") Integer id) {
        return serviInventario.buscarPorId(id);
    }

    @DeleteMapping("/inventario/{id}")
    public String eliminar(@PathVariable Integer id) {
        serviInventario.eliminar(id);
        return "Eliminado";
    }
}
