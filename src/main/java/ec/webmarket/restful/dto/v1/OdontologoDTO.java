package ec.webmarket.restful.dto.v1;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OdontologoDTO {
    private Long id;
    private String nombre;
    private String especialidad;
    private String telefono;
    private LocalDateTime fechaCreacion;
}
