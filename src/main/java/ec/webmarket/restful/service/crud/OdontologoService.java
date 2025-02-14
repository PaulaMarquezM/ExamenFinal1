package ec.webmarket.restful.service.crud;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.dto.v1.OdontologoDTO;
import ec.webmarket.restful.persistence.OdontologoRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OdontologoService extends GenericCrudServiceImpl<Odontologo, OdontologoDTO> {

    private final OdontologoRepository repository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository repository) {
        this.repository = repository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<OdontologoDTO> findAll() {
        return repository.findAll().stream()
                         .map(this::mapToDto)
                         .collect(Collectors.toList());
    }

    @Override
    public Optional<Odontologo> find(OdontologoDTO dto) {
        if (dto == null || dto.getId() == null) {
            return Optional.empty();
        }
        return repository.findById(dto.getId());
    }

    @Override
    public Odontologo mapToDomain(OdontologoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO no puede ser null");
        }
        return modelMapper.map(dto, Odontologo.class);
    }

    @Override
    public OdontologoDTO mapToDto(Odontologo domain) {
        if (domain == null) {
            throw new IllegalArgumentException("El dominio no puede ser null");
        }
        return modelMapper.map(domain, OdontologoDTO.class);
    }
}
