package ec.webmarket.restful.service.crud;

import ec.webmarket.restful.domain.Cita;
import ec.webmarket.restful.dto.v1.CitaDTO;
import ec.webmarket.restful.persistence.CitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaService {

    private final CitaRepository repository;
    private final ModelMapper modelMapper;

    public CitaService(CitaRepository repository) {
        this.repository = repository;
        this.modelMapper = new ModelMapper();
    }

    public CitaDTO create(CitaDTO dto) {
        Cita cita = modelMapper.map(dto, Cita.class);
        Cita savedCita = repository.save(cita);
        return modelMapper.map(savedCita, CitaDTO.class);
    }

    public CitaDTO update(Long id, CitaDTO dto) {
        Optional<Cita> optionalCita = repository.findById(id);
        if (optionalCita.isPresent()) {
            Cita cita = optionalCita.get();
            modelMapper.map(dto, cita);
            Cita updatedCita = repository.save(cita);
            return modelMapper.map(updatedCita, CitaDTO.class);
        }
        return null;
    }

    public boolean cancel(Long id) {
        Optional<Cita> optionalCita = repository.findById(id);
        if (optionalCita.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CitaDTO> findAll() {
        return repository.findAll().stream()
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .collect(Collectors.toList());
    }

    public List<CitaDTO> findByPaciente(Long pacienteId) {
        return repository.findAll().stream()
                .filter(cita -> cita.getPaciente().getId().equals(pacienteId))
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .collect(Collectors.toList());
    }

    public List<CitaDTO> findByOdontologo(Long odontologoId) {
        return repository.findAll().stream()
                .filter(cita -> cita.getOdontologo().getId().equals(odontologoId))
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .collect(Collectors.toList());
    }
}