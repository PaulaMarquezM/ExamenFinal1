package ec.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.domain.Usuario;
import ec.webmarket.restful.dto.v1.PacienteDTO;
import ec.webmarket.restful.persistence.PacienteRepository;
import ec.webmarket.restful.persistence.UsuarioRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class PacienteService extends GenericCrudServiceImpl<Paciente, PacienteDTO> {

    @Autowired
    private PacienteRepository repository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Paciente> find(PacienteDTO dto) {
        return repository.findById(dto.getId());
    }

    public List<Paciente> findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    public Optional<Paciente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(PacienteDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("El ID del paciente es obligatorio para eliminar.");
        }
        repository.deleteById(dto.getId());
    }
    
    @Override
    public Paciente mapToDomain(PacienteDTO dto) {
        return modelMapper.map(dto, Paciente.class);
    }

    @Override
    public PacienteDTO mapToDto(Paciente domain) {
        return modelMapper.map(domain, PacienteDTO.class);
    }

    public Optional<Paciente> findByNumeroCedula(Long numeroCedula) {
        return repository.findByNumeroCedula(numeroCedula);
    }

   
    public PacienteDTO create(PacienteDTO dto) {
        validarDatosPaciente(dto);
        
        
        Usuario usuario = usuarioRepository.findById(dto.getUsuario().getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró un usuario con ID: " + dto.getUsuario().getId()));

  
        Paciente paciente = mapToDomain(dto);
        paciente.setUsuario(usuario);

    
        paciente = repository.save(paciente);
        return mapToDto(paciente);
    }

   
    public PacienteDTO update(PacienteDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("El ID es obligatorio para actualizar el paciente.");
        }

        if (!repository.existsById(dto.getId())) {
            throw new IllegalArgumentException("No se encontró un paciente con el ID proporcionado.");
        }

        validarDatosPaciente(dto);

      
        Usuario usuario = usuarioRepository.findById(dto.getUsuario().getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró un usuario con ID: " + dto.getUsuario().getId()));

   
        Paciente paciente = mapToDomain(dto);
        paciente.setUsuario(usuario);

     
        paciente = repository.save(paciente);
        return mapToDto(paciente);
    }

  
    private void validarDatosPaciente(PacienteDTO dto) {
        if (dto.getNumeroCedula() == null) {
            throw new IllegalArgumentException("El campo numeroCedula es obligatorio.");
        }
        if (dto.getUsuario() == null || dto.getUsuario().getId() == null) {
            throw new IllegalArgumentException("El usuario es obligatorio y debe tener un ID.");
        }
    }
}
