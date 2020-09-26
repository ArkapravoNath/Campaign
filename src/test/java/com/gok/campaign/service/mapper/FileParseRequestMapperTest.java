package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParseRequestMapperTest {

    private FileParseRequestMapper fileParseRequestMapper;

    @BeforeEach
    public void setUp() {
        fileParseRequestMapper = new FileParseRequestMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fileParseRequestMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fileParseRequestMapper.fromId(null)).isNull();
    }
}
