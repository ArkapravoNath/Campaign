package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.DataColumnDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DataColumn} and its DTO {@link DataColumnDTO}.
 */
@Mapper(componentModel = "spring", uses = {DataRowMapper.class, ColumnMasterMapper.class})
public interface DataColumnMapper extends EntityMapper<DataColumnDTO, DataColumn> {

    @Mapping(source = "row.id", target = "rowId")
    @Mapping(source = "column.id", target = "columnId")
    DataColumnDTO toDto(DataColumn dataColumn);

    @Mapping(source = "rowId", target = "row")
    @Mapping(source = "columnId", target = "column")
    DataColumn toEntity(DataColumnDTO dataColumnDTO);

    default DataColumn fromId(Long id) {
        if (id == null) {
            return null;
        }
        DataColumn dataColumn = new DataColumn();
        dataColumn.setId(id);
        return dataColumn;
    }
}
