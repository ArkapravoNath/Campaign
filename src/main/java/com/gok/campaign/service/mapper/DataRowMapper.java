package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.DataRowDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DataRow} and its DTO {@link DataRowDTO}.
 */
@Mapper(componentModel = "spring", uses = {FileMapper.class})
public interface DataRowMapper extends EntityMapper<DataRowDTO, DataRow> {

    @Mapping(source = "file.id", target = "fileId")
    DataRowDTO toDto(DataRow dataRow);

    @Mapping(source = "fileId", target = "file")
    DataRow toEntity(DataRowDTO dataRowDTO);

    default DataRow fromId(Long id) {
        if (id == null) {
            return null;
        }
        DataRow dataRow = new DataRow();
        dataRow.setId(id);
        return dataRow;
    }
}
