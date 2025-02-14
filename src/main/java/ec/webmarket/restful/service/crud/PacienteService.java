package ec.webmarket.restful.service.crud;

import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.dto.v1.PacienteDTO;
import ec.webmarket.restful.persistence.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;


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

    public Optional<PacienteDTO> actualizarPaciente(Long id, PacienteDTO pacienteDTO) {
        if (!pacienteRepository.existsById(id)) {
            return Optional.empty();
        }
        Paciente paciente = toEntity(pacienteDTO);
        paciente.setId(id);
        return Optional.of(toDTO(pacienteRepository.save(paciente)));
    }

    private PacienteDTO toDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNumeroCedula(paciente.getNumeroCedula());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setTelefono(paciente.getTelefono());
        dto.setEmail(paciente.getEmail());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        dto.setDireccionResidencia(paciente.getDireccionResidencia());
        return dto;
    }

    private Paciente toEntity(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setId(dto.getId());
        paciente.setNumeroCedula(dto.getNumeroCedula());
        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setTelefono(dto.getTelefono());
        paciente.setEmail(dto.getEmail());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setDireccionResidencia(dto.getDireccionResidencia());
        return paciente;
    }
}