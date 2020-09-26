package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataRowMapperTest {

    private DataRowMapper dataRowMapper;

    @BeforeEach
    public void setUp() {
        dataRowMapper = new DataRowMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dataRowMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dataRowMapper.fromId(null)).isNull();
    }
}
