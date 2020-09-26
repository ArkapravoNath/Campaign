package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class ServiceReqDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceReqDTO.class);
        ServiceReqDTO serviceReqDTO1 = new ServiceReqDTO();
        serviceReqDTO1.setId(1L);
        ServiceReqDTO serviceReqDTO2 = new ServiceReqDTO();
        assertThat(serviceReqDTO1).isNotEqualTo(serviceReqDTO2);
        serviceReqDTO2.setId(serviceReqDTO1.getId());
        assertThat(serviceReqDTO1).isEqualTo(serviceReqDTO2);
        serviceReqDTO2.setId(2L);
        assertThat(serviceReqDTO1).isNotEqualTo(serviceReqDTO2);
        serviceReqDTO1.setId(null);
        assertThat(serviceReqDTO1).isNotEqualTo(serviceReqDTO2);
    }
}
