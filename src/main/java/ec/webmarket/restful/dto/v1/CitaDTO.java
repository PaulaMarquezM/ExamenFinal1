package ec.webmarket.restful.dto.v1;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CitaDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private String tipoConsulta;
    private Long pacienteId;
    private Long odontologoId;
    private Long horarioId;
    private LocalDateTime fechaCreacion;
    private String estado;
    private String motivoConsulta;
}