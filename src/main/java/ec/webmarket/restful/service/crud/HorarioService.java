package ec.webmarket.restful.service.crud;

import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.persistence.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository repository;

    public List<HorarioDTO> findAll() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<HorarioDTO> findByDisponibilidad(boolean disponible) {
        return repository.findByDisponible(disponible).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<HorarioDTO> findByOdontologo(Long odontologoId) {
        return repository.findByOdontologoId(odontologoId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<HorarioDTO> findByFecha(LocalDateTime start, LocalDateTime end) {
        return repository.findByInicioBetween(start, end).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public HorarioDTO create(HorarioDTO dto) {
        Horario horario = convertToEntity(dto);
        horario.setDisponible(true); // Horario por defecto está disponible
        return convertToDTO(repository.save(horario));
    }

    public HorarioDTO updateHorario(Long id, HorarioDTO dto) {
        return repository.findById(id).map(horario -> {
            horario.setInicio(dto.getInicio());
            horario.setFin(dto.getFin());
            horario.setDisponible(dto.isDisponible());
            return convertToDTO(repository.save(horario));
        }).orElseThrow(() -> new RuntimeException("Horario no encontrado"));
    }

    private HorarioDTO convertToDTO(Horario horario) {
        HorarioDTO dto = new HorarioDTO();
        dto.setId(horario.getId());
        dto.setInicio(horario.getInicio());
        dto.setFin(horario.getFin());
        dto.setOdontologoId(horario.getOdontologo().getId());
        dto.setDisponible(horario.isDisponible());
        return dto;
    }

    private Horario convertToEntity(HorarioDTO dto) {
        Horario horario = new Horario();
        horario.setId(dto.getId());
        horario.setInicio(dto.getInicio());
        horario.setFin(dto.getFin());
        horario.setDisponible(dto.isDisponible());
        return horario;
    }
}
