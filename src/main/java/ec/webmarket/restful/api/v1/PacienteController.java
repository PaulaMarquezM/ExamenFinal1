package ec.webmarket.restful.api.v1;

import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.PacienteDTO;
import ec.webmarket.restful.service.crud.PacienteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.URI_API_V1_PACIENTE) 
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> crearPaciente(@RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO nuevoPaciente = pacienteService.crearPaciente(pacienteDTO);
        return ResponseEntity.ok(nuevoPaciente);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> obtenerPacientes() {
        return ResponseEntity.ok(pacienteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> obtenerPacientePorId(@PathVariable Long id) {
        return pacienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> actualizarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        return pacienteService.actualizarPaciente(id, pacienteDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        if (pacienteService.eliminarPaciente(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
