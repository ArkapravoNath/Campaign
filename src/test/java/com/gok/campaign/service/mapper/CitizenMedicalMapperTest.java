package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CitizenMedicalMapperTest {

    private CitizenMedicalMapper citizenMedicalMapper;

    @BeforeEach
    public void setUp() {
        citizenMedicalMapper = new CitizenMedicalMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(citizenMedicalMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(citizenMedicalMapper.fromId(null)).isNull();
    }
}
