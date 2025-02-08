package ec.webmarket.restful.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.webmarket.restful.dto.v1.*;
import ec.webmarket.restful.service.crud.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/horario")
public class HorarioController {
    @Autowired
    private HorarioService service;

    @GetMapping
    public ResponseEntity<List<HorarioDTO>> getAll() {
        return ResponseEntity.ok(service.findAll(null));
    }

    @PostMapping
    public ResponseEntity<HorarioDTO> create(@RequestBody HorarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
}