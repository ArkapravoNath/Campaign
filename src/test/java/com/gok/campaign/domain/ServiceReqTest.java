package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class ServiceReqTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceReq.class);
        ServiceReq serviceReq1 = new ServiceReq();
        serviceReq1.setId(1L);
        ServiceReq serviceReq2 = new ServiceReq();
        serviceReq2.setId(serviceReq1.getId());
        assertThat(serviceReq1).isEqualTo(serviceReq2);
        serviceReq2.setId(2L);
        assertThat(serviceReq1).isNotEqualTo(serviceReq2);
        serviceReq1.setId(null);
        assertThat(serviceReq1).isNotEqualTo(serviceReq2);
    }
}
