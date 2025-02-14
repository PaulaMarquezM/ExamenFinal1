package ec.webmarket.restful.api.v1;

import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.service.crud.HorarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.URI_API_V1_HORARIO)
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @PostMapping
    public ResponseEntity<HorarioDTO> crearHorario(@RequestBody HorarioDTO horarioDTO) {
        return ResponseEntity.ok(horarioService.crearHorario(horarioDTO));
    }

    @GetMapping("/odontologo/{odontologoId}")
    public ResponseEntity<List<HorarioDTO>> obtenerHorariosPorOdontologo(@PathVariable Long odontologoId) {
        return ResponseEntity.ok(horarioService.obtenerHorariosPorOdontologo(odontologoId));
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<HorarioDTO>> obtenerHorariosPorFecha(@PathVariable LocalDate fecha) {
        return ResponseEntity.ok(horarioService.obtenerHorariosPorFecha(fecha));
    }

    @GetMapping("/disponibles/{estado}")
    public ResponseEntity<List<HorarioDTO>> obtenerHorariosDisponibles(@PathVariable boolean estado) {
        return ResponseEntity.ok(horarioService.obtenerHorariosDisponibles(estado));
    }
}
