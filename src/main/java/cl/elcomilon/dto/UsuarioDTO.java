package cl.elcomilon.dto;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Formato de email inválido")
    private String email;

    @Size(min=6, message="La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;
}
