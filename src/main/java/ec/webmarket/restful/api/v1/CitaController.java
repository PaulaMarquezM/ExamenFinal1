package ec.webmarket.restful.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.*;
import ec.webmarket.restful.service.crud.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.URI_API_V1_CITA)
public class CitaController {
    @Autowired
    private CitaService service;

    @GetMapping
    public ResponseEntity<List<CitaDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<CitaDTO> create(@RequestBody CitaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> update(@PathVariable Long id, @RequestBody CitaDTO dto) {
        CitaDTO updated = service.update(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        boolean canceled = service.cancel(id);
        if (canceled) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<CitaDTO>> getHistorialByPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByPaciente(id));
    }
    
    @GetMapping("/odontologo/{id}")
    public ResponseEntity<List<CitaDTO>> getHistorialByOdontologo(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByOdontologo(id));
    }
}