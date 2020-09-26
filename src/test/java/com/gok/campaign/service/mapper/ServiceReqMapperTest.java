package com.gok.campaign.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ServiceReqMapperTest {

    private ServiceReqMapper serviceReqMapper;

    @BeforeEach
    public void setUp() {
        serviceReqMapper = new ServiceReqMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(serviceReqMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(serviceReqMapper.fromId(null)).isNull();
    }
}
