package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.ZoneTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ZoneType} and its DTO {@link ZoneTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ZoneTypeMapper extends EntityMapper<ZoneTypeDTO, ZoneType> {


    @Mapping(target = "zones", ignore = true)
    @Mapping(target = "removeZone", ignore = true)
    ZoneType toEntity(ZoneTypeDTO zoneTypeDTO);

    default ZoneType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ZoneType zoneType = new ZoneType();
        zoneType.setId(id);
        return zoneType;
    }
}
