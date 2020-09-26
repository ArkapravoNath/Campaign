package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.AnswerSetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnswerSet} and its DTO {@link AnswerSetDTO}.
 */
@Mapper(componentModel = "spring", uses = {FileMapper.class, QuestionSetMapper.class})
public interface AnswerSetMapper extends EntityMapper<AnswerSetDTO, AnswerSet> {

    @Mapping(source = "file.id", target = "fileId")
    @Mapping(source = "questionSet.id", target = "questionSetId")
    AnswerSetDTO toDto(AnswerSet answerSet);

    @Mapping(target = "answers", ignore = true)
    @Mapping(target = "removeAnswer", ignore = true)
    @Mapping(source = "fileId", target = "file")
    @Mapping(source = "questionSetId", target = "questionSet")
    AnswerSet toEntity(AnswerSetDTO answerSetDTO);

    default AnswerSet fromId(Long id) {
        if (id == null) {
            return null;
        }
        AnswerSet answerSet = new AnswerSet();
        answerSet.setId(id);
        return answerSet;
    }
}
