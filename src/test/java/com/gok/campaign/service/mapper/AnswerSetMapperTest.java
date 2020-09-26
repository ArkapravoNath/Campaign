package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswerSetMapperTest {

    private AnswerSetMapper answerSetMapper;

    @BeforeEach
    public void setUp() {
        answerSetMapper = new AnswerSetMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(answerSetMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(answerSetMapper.fromId(null)).isNull();
    }
}
