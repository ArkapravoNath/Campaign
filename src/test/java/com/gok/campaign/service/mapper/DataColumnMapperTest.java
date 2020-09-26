package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataColumnMapperTest {

    private DataColumnMapper dataColumnMapper;

    @BeforeEach
    public void setUp() {
        dataColumnMapper = new DataColumnMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dataColumnMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dataColumnMapper.fromId(null)).isNull();
    }
}
