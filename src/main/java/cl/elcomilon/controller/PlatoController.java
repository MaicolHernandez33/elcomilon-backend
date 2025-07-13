package cl.elcomilon.controller;
import cl.elcomilon.model.Plato;
import cl.elcomilon.dto.PlatoDTO;
import cl.elcomilon.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import cl.elcomilon.repository.*;

@RestController
@RequestMapping("/api/platos")
@CrossOrigin(origins = "*")
public class PlatoController {
    @Autowired
    private PlatoService platoService;

    @GetMapping
    public ResponseEntity<List<Plato>> getAll() {
        return ResponseEntity.ok(platoService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PlatoDTO platoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(platoService.save(platoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        boolean borrado = platoService.deleteByIdChecked(id);
        if (!borrado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }



}
