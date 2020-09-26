package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdditionalSymptomsMapperTest {

    private AdditionalSymptomsMapper additionalSymptomsMapper;

    @BeforeEach
    public void setUp() {
        additionalSymptomsMapper = new AdditionalSymptomsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(additionalSymptomsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(additionalSymptomsMapper.fromId(null)).isNull();
    }
}
