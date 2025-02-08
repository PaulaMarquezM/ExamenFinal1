package ec.webmarket.restful.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.webmarket.restful.domain.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByOdontologoId(Long odontologoId);
}
