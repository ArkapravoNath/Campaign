package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionSetMapperTest {

    private QuestionSetMapper questionSetMapper;

    @BeforeEach
    public void setUp() {
        questionSetMapper = new QuestionSetMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(questionSetMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(questionSetMapper.fromId(null)).isNull();
    }
}
