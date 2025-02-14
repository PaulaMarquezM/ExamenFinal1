package ec.webmarket.restful.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.OdontologoDTO;
import ec.webmarket.restful.service.crud.OdontologoService;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController

@RequestMapping(ApiConstants.URI_API_V1_ODONTOLOGO) 

public class OdontologoController {
    @Autowired
    private OdontologoService service;


    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> getAll1() {
        return ResponseEntity.ok(service.findAll(null));
    }

    @PostMapping
    public ResponseEntity<OdontologoDTO> create(@RequestBody OdontologoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }


    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> getAll() {
        return ResponseEntity.ok(service.findAll(null)); 
    }
}

