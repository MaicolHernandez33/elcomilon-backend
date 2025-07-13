package cl.elcomilon.service;

import cl.elcomilon.dto.PlatoDTO;
import cl.elcomilon.model.Plato;
import cl.elcomilon.model.Proveedor;
import cl.elcomilon.repository.PlatoRepository;
import cl.elcomilon.repository.ProveedorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlatoService {
    @Autowired
    private PlatoRepository platoRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Plato> getAll() {
        return platoRepository.findAll();
    }

    // Guardar plato directamente como entidad
    public Plato save(Plato plato) {
        return platoRepository.save(plato);
    }

    public void delete(Long id) {
        platoRepository.deleteById(id);
    }

    // Guardar desde un DTO
    public Plato save(@Valid PlatoDTO dto) {
        Plato plato = new Plato();
        plato.setNombre(dto.getNombre());
        plato.setDescripcion(dto.getDescripcion());
        plato.setPrecio(dto.getPrecio());

        // Nuevo: guardar la URL de la imagen
        plato.setImagenUrl(dto.getImagenUrl());

        // Nuevo: guardar las unidades disponibles
        plato.setDisponibleUnidades(dto.getDisponibleUnidades());

        // Nuevo: booleano disponible (puedes dejarlo como está o gestionarlo aparte)
        plato.setDisponible(dto.isDisponible());

        // Relación proveedor (opcional, solo si idProveedor no es null)
        if (dto.getIdProveedor() != null) {
            Proveedor proveedor = proveedorRepository.findById(dto.getIdProveedor()).orElse(null);
            plato.setProveedor(proveedor);
        } else {
            plato.setProveedor(null);
        }

        return platoRepository.save(plato);
    }

    public boolean deleteByIdChecked(Long id) {
        if (!platoRepository.existsById(id)) {
            return false;
        }
        platoRepository.deleteById(id);
        return true;
    }
}
