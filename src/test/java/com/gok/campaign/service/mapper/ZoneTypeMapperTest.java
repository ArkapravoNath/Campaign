package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ZoneTypeMapperTest {

    private ZoneTypeMapper zoneTypeMapper;

    @BeforeEach
    public void setUp() {
        zoneTypeMapper = new ZoneTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(zoneTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(zoneTypeMapper.fromId(null)).isNull();
    }
}
