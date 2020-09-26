package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.ColumnMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ColumnMaster} and its DTO {@link ColumnMasterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ColumnMasterMapper extends EntityMapper<ColumnMasterDTO, ColumnMaster> {



    default ColumnMaster fromId(Long id) {
        if (id == null) {
            return null;
        }
        ColumnMaster columnMaster = new ColumnMaster();
        columnMaster.setId(id);
        return columnMaster;
    }
}
