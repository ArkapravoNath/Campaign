package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.AnswerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Answer} and its DTO {@link AnswerDTO}.
 */
@Mapper(componentModel = "spring", uses = {AnswerSetMapper.class})
public interface AnswerMapper extends EntityMapper<AnswerDTO, Answer> {

    @Mapping(source = "answerSet.id", target = "answerSetId")
    AnswerDTO toDto(Answer answer);

    @Mapping(source = "answerSetId", target = "answerSet")
    Answer toEntity(AnswerDTO answerDTO);

    default Answer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Answer answer = new Answer();
        answer.setId(id);
        return answer;
    }
}
