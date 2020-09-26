package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.CitizenMedicalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CitizenMedical} and its DTO {@link CitizenMedicalDTO}.
 */
@Mapper(componentModel = "spring", uses = {MedicalSymptomMapper.class, AdditionalSymptomsMapper.class})
public interface CitizenMedicalMapper extends EntityMapper<CitizenMedicalDTO, CitizenMedical> {

    @Mapping(source = "medicalSysmptom.id", target = "medicalSysmptomId")
    @Mapping(source = "additionalSymptoms.id", target = "additionalSymptomsId")
    CitizenMedicalDTO toDto(CitizenMedical citizenMedical);

    @Mapping(source = "medicalSysmptomId", target = "medicalSysmptom")
    @Mapping(source = "additionalSymptomsId", target = "additionalSymptoms")
    CitizenMedical toEntity(CitizenMedicalDTO citizenMedicalDTO);

    default CitizenMedical fromId(Long id) {
        if (id == null) {
            return null;
        }
        CitizenMedical citizenMedical = new CitizenMedical();
        citizenMedical.setId(id);
        return citizenMedical;
    }
}
