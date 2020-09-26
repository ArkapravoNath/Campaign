package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.ZoneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Zone} and its DTO {@link ZoneDTO}.
 */
@Mapper(componentModel = "spring", uses = {ZoneTypeMapper.class, LocationMapper.class})
public interface ZoneMapper extends EntityMapper<ZoneDTO, Zone> {


    @Mapping(target = "removeZoneType", ignore = true)
    @Mapping(target = "removeLocation", ignore = true)

    default Zone fromId(Long id) {
        if (id == null) {
            return null;
        }
        Zone zone = new Zone();
        zone.setId(id);
        return zone;
    }
}
