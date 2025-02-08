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
public class CitaService extends GenericCrudServiceImpl<Cita, CitaDTO> {
    @Autowired
    private CitaRepository repository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Cita> find(CitaDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public Cita mapToDomain(CitaDTO dto) {
        return modelMapper.map(dto, Cita.class);
    }

    @Override
    public CitaDTO mapToDto(Cita domain) {
        return modelMapper.map(domain, CitaDTO.class);
    }

    public void cancelarCita(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró una cita con el ID: " + id);
        }
        repository.deleteById(id);
    }
}
