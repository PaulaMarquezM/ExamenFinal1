package ec.webmarket.restful.service.crud;

import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.domain.Usuario;
import ec.webmarket.restful.dto.v1.OdontologoDTO;
import ec.webmarket.restful.persistence.OdontologoRepository;
import ec.webmarket.restful.persistence.UsuarioRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OdontologoService {

    private final OdontologoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository repository,  UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = new ModelMapper();
    }

    public OdontologoDTO create(OdontologoDTO dto) {
        validarDatosOdontologo(dto);
        Usuario usuario = usuarioRepository.findById(dto.getUsuario().getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró un usuario con ID: " + dto.getUsuario().getId()));

        Odontologo odontologo = modelMapper.map(dto, Odontologo.class);
        odontologo.setUsuario(usuario);

       
        Odontologo savedOdontologo = repository.save(odontologo);
        return modelMapper.map(savedOdontologo, OdontologoDTO.class);
    }

    public List<OdontologoDTO> findAll() {
        return repository.findAll().stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoDTO.class))
                .collect(Collectors.toList());
    }

    public OdontologoDTO findById(Long id) {
        Optional<Odontologo> optionalOdontologo = repository.findById(id);
        return optionalOdontologo.map(odontologo -> modelMapper.map(odontologo, OdontologoDTO.class)).orElse(null);
    }
    
    private void validarDatosOdontologo(OdontologoDTO dto) {
        if (dto.getNumeroCedula() == null) {
            throw new IllegalArgumentException("El campo numeroCedula es obligatorio.");
        }
        if (dto.getUsuario() == null || dto.getUsuario().getId() == null) {
            throw new IllegalArgumentException("El usuario es obligatorio y debe tener un ID.");
        }
    }
}
