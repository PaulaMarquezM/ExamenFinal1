package ec.webmarket.restful.api.v1;

import static ec.webmarket.restful.common.ApiConstants.URI_API_V1_HORARIO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.service.crud.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(URI_API_V1_HORARIO)
public class HorarioController {
    @Autowired
    private HorarioService service;

    @GetMapping
    public ResponseEntity<List<HorarioDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<HorarioDTO> create(@RequestBody HorarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }


    @GetMapping("/disponibles")
    public ResponseEntity<List<HorarioDTO>> getDisponibles() {
        return ResponseEntity.ok(service.findByDisponibilidad(true));
    }


    @GetMapping("/odontologo/{id}")
    public ResponseEntity<List<HorarioDTO>> getByOdontologo(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByOdontologo(id));
    }


    @GetMapping("/fecha")
    public ResponseEntity<List<HorarioDTO>> getByFecha(@RequestParam String fecha) {
        LocalDateTime start = LocalDateTime.parse(fecha + "T00:00:00");
        LocalDateTime end = LocalDateTime.parse(fecha + "T23:59:59");
        return ResponseEntity.ok(service.findByFecha(start, end));
    }


    @PutMapping("/{id}")
    public ResponseEntity<HorarioDTO> updateHorario(@PathVariable Long id, @RequestBody HorarioDTO dto) {
        return ResponseEntity.ok(service.updateHorario(id, dto));
    }
}
