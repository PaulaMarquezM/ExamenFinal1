package ec.webmarket.restful.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.OdontologoDTO;
import ec.webmarket.restful.service.crud.OdontologoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiConstants.URI_API_V1_ODONTOLOGO) // ✅ Usa la constante para la ruta
public class OdontologoController {

    private final OdontologoService service;

    public OdontologoController(OdontologoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> getAll() {
    	 return ResponseEntity.ok(service.findAll()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> getById(@PathVariable Long id) {
        Optional<OdontologoDTO> odontologo = service.findById(id);
        return odontologo.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OdontologoDTO> create(@RequestBody OdontologoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OdontologoDTO> update(@PathVariable Long id, @RequestBody OdontologoDTO dto) {
        Optional<OdontologoDTO> updated = service.update(id, dto);
        return updated.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
