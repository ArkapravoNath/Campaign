package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.CampTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CampType} and its DTO {@link CampTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CampTypeMapper extends EntityMapper<CampTypeDTO, CampType> {



    default CampType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CampType campType = new CampType();
        campType.setId(id);
        return campType;
    }
}
