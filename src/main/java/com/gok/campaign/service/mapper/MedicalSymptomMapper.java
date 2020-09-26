package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.MedicalSymptomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalSymptom} and its DTO {@link MedicalSymptomDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedicalSymptomMapper extends EntityMapper<MedicalSymptomDTO, MedicalSymptom> {



    default MedicalSymptom fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalSymptom medicalSymptom = new MedicalSymptom();
        medicalSymptom.setId(id);
        return medicalSymptom;
    }
}
