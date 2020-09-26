package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EssentialsMapperTest {

    private EssentialsMapper essentialsMapper;

    @BeforeEach
    public void setUp() {
        essentialsMapper = new EssentialsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(essentialsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(essentialsMapper.fromId(null)).isNull();
    }
}
