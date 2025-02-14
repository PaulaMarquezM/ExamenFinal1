package ec.webmarket.restful.dto.v1;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
}
