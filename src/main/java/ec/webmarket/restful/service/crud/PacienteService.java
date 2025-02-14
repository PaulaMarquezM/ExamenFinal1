package ec.webmarket.restful.service.crud;

import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.dto.v1.PacienteDTO;
import ec.webmarket.restful.persistence.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteDTO crearPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = toEntity(pacienteDTO);
        paciente = pacienteRepository.save(paciente);
        return toDTO(paciente);
    }

    public List<PacienteDTO> obtenerTodos() {
        return pacienteRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<PacienteDTO> obtenerPorId(Long id) {
        return pacienteRepository.findById(id).map(this::toDTO);
    }

    public Optional<PacienteDTO> actualizarPaciente(Long id, PacienteDTO pacienteDTO) {
        if (!pacienteRepository.existsById(id)) {
            return Optional.empty();
        }
        Paciente paciente = toEntity(pacienteDTO);
        paciente.setId(id);
        return Optional.of(toDTO(pacienteRepository.save(paciente)));
    }

    public boolean eliminarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            return false;
        }
        pacienteRepository.deleteById(id);
        return true;
    }

    private PacienteDTO toDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setTelefono(paciente.getTelefono());
        dto.setEmail(paciente.getEmail());
      
        return dto;
    }

    private Paciente toEntity(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setId(dto.getId());
        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setTelefono(dto.getTelefono());
        paciente.setEmail(dto.getEmail());
        return paciente;
    }
}
