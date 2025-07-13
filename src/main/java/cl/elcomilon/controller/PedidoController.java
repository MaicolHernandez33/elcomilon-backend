package cl.elcomilon.controller;
import cl.elcomilon.model.Pedido;
import cl.elcomilon.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> getAll() { return pedidoService.getAll(); }

    @PostMapping
    public Pedido save(@RequestBody Pedido pedido) { return pedidoService.save(pedido); }

    @GetMapping("/{id}")
    public Pedido getById(@PathVariable Long id) { return pedidoService.getById(id); }
}