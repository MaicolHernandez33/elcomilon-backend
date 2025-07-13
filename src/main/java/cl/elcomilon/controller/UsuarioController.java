package cl.elcomilon.controller;
import cl.elcomilon.dto.UsuarioDTO;
import cl.elcomilon.model.Usuario;
import cl.elcomilon.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(usuarioService.registrar(usuarioDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO loginDTO) {
        Optional<Usuario> user = usuarioService.login(loginDTO.getEmail(), loginDTO.getPassword());
        if (user.isEmpty()) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        return ResponseEntity.ok(user.get());
    }
    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    // Obtener usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    // Otros endpoints con ResponseEntity...
}
