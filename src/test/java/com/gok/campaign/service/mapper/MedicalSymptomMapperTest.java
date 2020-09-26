package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedicalSymptomMapperTest {

    private MedicalSymptomMapper medicalSymptomMapper;

    @BeforeEach
    public void setUp() {
        medicalSymptomMapper = new MedicalSymptomMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medicalSymptomMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medicalSymptomMapper.fromId(null)).isNull();
    }
}
