package cl.elcomilon.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Usuario cliente;
    private LocalDateTime fechaHoraPedido;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoDetalle> detalles;
    private String estado; // CREADO, EN_PROCESO, EN_REPARTO, ENTREGADO,CANCELADO
    private String direccionEntrega;
    private String metodoPago;
    private Integer total;
}
