package cl.elcomilon.service;
import cl.elcomilon.model.Cuenta;
import cl.elcomilon.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;
    public Optional<Cuenta> getByUsuarioId(Long usuarioId) {
        return cuentaRepository.findByUsuarioId(usuarioId);
    }
    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }
}