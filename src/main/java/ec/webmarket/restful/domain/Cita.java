package ec.webmarket.restful.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaHora;
    private String tipoConsulta;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Odontologo odontologo;
}