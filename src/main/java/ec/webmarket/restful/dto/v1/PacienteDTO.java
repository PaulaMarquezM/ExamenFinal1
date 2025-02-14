package ec.webmarket.restful.dto.v1;


import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDateTime fechaCreacion;
}