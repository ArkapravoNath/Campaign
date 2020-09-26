package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.ServiceReqDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceReq} and its DTO {@link ServiceReqDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdditionalSymptomsMapper.class})
public interface ServiceReqMapper extends EntityMapper<ServiceReqDTO, ServiceReq> {

    @Mapping(source = "additionalSymptoms.id", target = "additionalSymptomsId")
    ServiceReqDTO toDto(ServiceReq serviceReq);

    @Mapping(target = "essentials", ignore = true)
    @Mapping(source = "additionalSymptomsId", target = "additionalSymptoms")
    ServiceReq toEntity(ServiceReqDTO serviceReqDTO);

    default ServiceReq fromId(Long id) {
        if (id == null) {
            return null;
        }
        ServiceReq serviceReq = new ServiceReq();
        serviceReq.setId(id);
        return serviceReq;
    }
}
