package kentakitos.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kentakitos.backend.entity.Categorias;
import kentakitos.backend.service.ICategoriasService;

@RestController
@RequestMapping("/kentakitos")

public class CategoriasController {
        @Autowired
    private ICategoriasService categoriasService;

    @GetMapping("/categorias")
    public List<Categorias> buscarTodos() {
        return categoriasService.buscarTodos();
    }
    @PostMapping("/categorias")
    public Categorias guardar(@RequestBody Categorias categoria) {
        categoriasService.guardar(categoria);
        return categoria;
    }
    @PutMapping("/categorias")
    public Categorias modificar(@RequestBody Categorias categoria) {
        categoriasService.modificar(categoria);
        return categoria;
    }
    @GetMapping("/categorias/{id}")
    public Optional<Categorias> buscarPorId(@PathVariable("id") Integer id) {
        return categoriasService.buscarPorId(id);
    }
    @DeleteMapping("/categorias/{id}")
    public String eliminar(@PathVariable Integer id) {
        categoriasService.eliminar(id);
        return "Eliminado";
    }
}
