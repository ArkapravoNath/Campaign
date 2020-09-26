package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.AdditionalSymptomsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdditionalSymptoms} and its DTO {@link AdditionalSymptomsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdditionalSymptomsMapper extends EntityMapper<AdditionalSymptomsDTO, AdditionalSymptoms> {


    @Mapping(target = "citizenMedicals", ignore = true)
    @Mapping(target = "removeCitizenMedical", ignore = true)
    @Mapping(target = "serviceReqs", ignore = true)
    @Mapping(target = "removeServiceReq", ignore = true)
    AdditionalSymptoms toEntity(AdditionalSymptomsDTO additionalSymptomsDTO);

    default AdditionalSymptoms fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdditionalSymptoms additionalSymptoms = new AdditionalSymptoms();
        additionalSymptoms.setId(id);
        return additionalSymptoms;
    }
}
