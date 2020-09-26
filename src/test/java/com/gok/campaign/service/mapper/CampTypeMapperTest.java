package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CampTypeMapperTest {

    private CampTypeMapper campTypeMapper;

    @BeforeEach
    public void setUp() {
        campTypeMapper = new CampTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(campTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(campTypeMapper.fromId(null)).isNull();
    }
}
