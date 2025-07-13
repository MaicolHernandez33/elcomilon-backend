package cl.elcomilon.service;
import cl.elcomilon.model.Pedido;
import cl.elcomilon.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }
    public Pedido getById(Long id) { return pedidoRepository.findById(id).orElse(null); }
}