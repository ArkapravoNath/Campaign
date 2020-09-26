package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.FileParseRequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FileParseRequest} and its DTO {@link FileParseRequestDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FileParseRequestMapper extends EntityMapper<FileParseRequestDTO, FileParseRequest> {



    default FileParseRequest fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileParseRequest fileParseRequest = new FileParseRequest();
        fileParseRequest.setId(id);
        return fileParseRequest;
    }
}
