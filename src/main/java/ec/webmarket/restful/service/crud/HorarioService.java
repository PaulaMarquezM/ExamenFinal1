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
public class HorarioService extends GenericCrudServiceImpl<Horario, HorarioDTO> {
    @Autowired
    private HorarioRepository repository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Horario> find(HorarioDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public Horario mapToDomain(HorarioDTO dto) {
        return modelMapper.map(dto, Horario.class);
    }

    @Override
    public HorarioDTO mapToDto(Horario domain) {
        return modelMapper.map(domain, HorarioDTO.class);
    }
}