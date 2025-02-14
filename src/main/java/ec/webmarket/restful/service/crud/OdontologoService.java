package ec.webmarket.restful.service.crud;

import ec.webmarket.restful.dto.v1.OdontologoDTO;
import java.util.List;

public interface OdontologoService {
    OdontologoDTO create(OdontologoDTO dto);
    List<OdontologoDTO> findAll();
}
