package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.EssentialsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Essentials} and its DTO {@link EssentialsDTO}.
 */
@Mapper(componentModel = "spring", uses = {ServiceReqMapper.class})
public interface EssentialsMapper extends EntityMapper<EssentialsDTO, Essentials> {

    @Mapping(source = "serviceReq.id", target = "serviceReqId")
    EssentialsDTO toDto(Essentials essentials);

    @Mapping(source = "serviceReqId", target = "serviceReq")
    Essentials toEntity(EssentialsDTO essentialsDTO);

    default Essentials fromId(Long id) {
        if (id == null) {
            return null;
        }
        Essentials essentials = new Essentials();
        essentials.setId(id);
        return essentials;
    }
}
