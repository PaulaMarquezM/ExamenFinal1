package ec.webmarket.restful.service.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.*;
import ec.webmarket.restful.dto.v1.*;
import ec.webmarket.restful.persistence.*;
import ec.webmarket.restful.service.GenericCrudServiceImpl;
import java.util.Optional;


@Service
public class PacienteService extends GenericCrudServiceImpl<Paciente, PacienteDTO> {
    @Autowired
    private PacienteRepository repository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Paciente> find(PacienteDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public Paciente mapToDomain(PacienteDTO dto) {
        return modelMapper.map(dto, Paciente.class);
    }

    @Override
    public PacienteDTO mapToDto(Paciente domain) {
        return modelMapper.map(domain, PacienteDTO.class);
    }
}
