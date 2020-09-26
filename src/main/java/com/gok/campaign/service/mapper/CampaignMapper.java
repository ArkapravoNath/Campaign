package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.CampaignDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Campaign} and its DTO {@link CampaignDTO}.
 */
@Mapper(componentModel = "spring", uses = {OwnerMapper.class, LocationMapper.class, ZoneMapper.class, QuestionSetMapper.class, CampTypeMapper.class})
public interface CampaignMapper extends EntityMapper<CampaignDTO, Campaign> {

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "zone.id", target = "zoneId")
    @Mapping(source = "questionSet.id", target = "questionSetId")
    @Mapping(source = "type.id", target = "typeId")
    CampaignDTO toDto(Campaign campaign);

    @Mapping(source = "parentId", target = "parent")
    @Mapping(source = "ownerId", target = "owner")
    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "zoneId", target = "zone")
    @Mapping(source = "questionSetId", target = "questionSet")
    @Mapping(source = "typeId", target = "type")
    Campaign toEntity(CampaignDTO campaignDTO);

    default Campaign fromId(Long id) {
        if (id == null) {
            return null;
        }
        Campaign campaign = new Campaign();
        campaign.setId(id);
        return campaign;
    }
}
