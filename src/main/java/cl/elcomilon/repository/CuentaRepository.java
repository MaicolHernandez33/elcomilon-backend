package cl.elcomilon.repository;

import cl.elcomilon.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> findByUsuarioId(Long usuarioId);
}
