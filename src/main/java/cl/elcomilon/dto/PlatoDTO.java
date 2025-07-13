package cl.elcomilon.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private String imagenUrl;
    private Integer disponibleUnidades;
    private Long idProveedor; // <-- solo el id del proveedor, opcional
    private boolean disponible;
}
