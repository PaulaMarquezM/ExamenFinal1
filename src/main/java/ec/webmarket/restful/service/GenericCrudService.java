package ec.webmarket.restful.service;

import java.util.List;
import java.util.Optional;

public interface GenericCrudService<DOMAIN, DTO> {

    DTO create(DTO dto);

    DTO update(DTO dto);

    void delete(DTO dto);

    Optional<DOMAIN> find(DTO dto);

    List<DTO> findAll(); 

    DOMAIN mapToDomain(DTO dto);

    DTO mapToDto(DOMAIN domain);
}
