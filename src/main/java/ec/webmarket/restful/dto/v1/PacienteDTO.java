package ec.webmarket.restful.dto.v1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO {
    private Long id;
    private Long numeroCedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccionResidencia;
    private UsuarioDTO usuario;  // ✅ Debe existir esta línea
}
