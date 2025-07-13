package cl.elcomilon.dto;

import lombok.Data;
import java.util.List;
@Data
public class PedidoDTO {
    private Long id;
    private Long clienteId;
    private String fechaHora;
    private List<PedidoDetalleDTO> detalles;
    private String estado;
    private String direccionEntrega;
    private String metodoPago;
    private Integer total;
}

