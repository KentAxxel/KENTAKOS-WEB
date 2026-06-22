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

import kentakitos.backend.entity.Categorias;
import kentakitos.backend.entity.CategoriasDTO;
import kentakitos.backend.service.ICategoriasService;

@RestController
@RequestMapping("/kentakitos")

public class CategoriasController {
    @Autowired
    private ICategoriasService serviCategorias;

    @GetMapping("/categorias")
    public List<Categorias> buscarTodos() {
        return serviCategorias.buscarTodos();
    }

    @PostMapping("/categorias")
    public ResponseEntity<?> guardar(@RequestBody CategoriasDTO dto) {
        Categorias categoria = new Categorias();
        categoria.setNombrecategoria(dto.getNombrecategoria());
        return ResponseEntity.ok(serviCategorias.guardar(categoria));
    }

    @PutMapping("/categorias")
    public ResponseEntity<?> modificar(@RequestBody CategoriasDTO dto) {

        if (dto.getIdcategoria() == null) {
            return ResponseEntity.badRequest().body("ID no existe");
        }

        Categorias categoria = new Categorias();
        categoria.setIdcategoria(dto.getIdcategoria());
        categoria.setNombrecategoria(dto.getNombrecategoria());
        
        return ResponseEntity.ok(serviCategorias.modificar(categoria));
    }

    @GetMapping("/categorias/{id}")
    public Optional<Categorias> buscarPorId(@PathVariable("id") Integer id) {
        return serviCategorias.buscarPorId(id);
    }

    @DeleteMapping("/categorias/{id}")
    public String eliminar(@PathVariable Integer id) {
        serviCategorias.eliminar(id);
        return "Eliminado";
    }
}
