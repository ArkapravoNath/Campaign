package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ColumnMasterMapperTest {

    private ColumnMasterMapper columnMasterMapper;

    @BeforeEach
    public void setUp() {
        columnMasterMapper = new ColumnMasterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(columnMasterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(columnMasterMapper.fromId(null)).isNull();
    }
}
