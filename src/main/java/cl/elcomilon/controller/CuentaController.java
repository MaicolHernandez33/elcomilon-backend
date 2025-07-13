package cl.elcomilon.controller;
import cl.elcomilon.model.Cuenta;
import cl.elcomilon.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
@CrossOrigin(origins = "*")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Cuenta> getByUsuario(@PathVariable("usuarioId") Long usuarioId) {
        return cuentaService.getByUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}