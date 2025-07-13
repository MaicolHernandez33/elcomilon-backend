package cl.elcomilon.service;
import cl.elcomilon.dto.UsuarioDTO;
import cl.elcomilon.model.Usuario;
import cl.elcomilon.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Guardar desde DTO (usado por tu controller)
    public Usuario registrar(@Valid UsuarioDTO usuarioDTO) {
        Usuario user = new Usuario();
        user.setNombre(usuarioDTO.getNombre());
        user.setEmail(usuarioDTO.getEmail());
        user.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        user.setRol(usuarioDTO.getRol());
        user.setActivo(true);
        return usuarioRepository.save(user);
    }

    public Optional<Usuario> login(String email, String password) {
        Optional<Usuario> optUser = usuarioRepository.findByEmail(email);
        if (optUser.isPresent() && passwordEncoder.matches(password, optUser.get().getPassword())) {
            return optUser;
        }
        return Optional.empty();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

}
