// Archivo: src/main/java/cl/elcomilon/model/Plato.java

package cl.elcomilon.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private String imagenUrl;

    // >>>>>>> Agrega este campo justo aquí, junto a los demás atributos simples:
    private Integer disponibleUnidades; // Unidades disponibles

    private boolean disponible;

    @ManyToOne
    private Proveedor proveedor; // null si es del restaurant principal
}
