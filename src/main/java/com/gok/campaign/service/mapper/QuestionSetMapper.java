package com.gok.campaign.service.mapper;


import com.gok.campaign.domain.*;
import com.gok.campaign.service.dto.QuestionSetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionSet} and its DTO {@link QuestionSetDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuestionMapper.class})
public interface QuestionSetMapper extends EntityMapper<QuestionSetDTO, QuestionSet> {


    @Mapping(target = "removeQuestion", ignore = true)

    default QuestionSet fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuestionSet questionSet = new QuestionSet();
        questionSet.setId(id);
        return questionSet;
    }
}
