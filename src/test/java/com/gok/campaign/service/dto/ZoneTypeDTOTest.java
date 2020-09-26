package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class ZoneTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ZoneTypeDTO.class);
        ZoneTypeDTO zoneTypeDTO1 = new ZoneTypeDTO();
        zoneTypeDTO1.setId(1L);
        ZoneTypeDTO zoneTypeDTO2 = new ZoneTypeDTO();
        assertThat(zoneTypeDTO1).isNotEqualTo(zoneTypeDTO2);
        zoneTypeDTO2.setId(zoneTypeDTO1.getId());
        assertThat(zoneTypeDTO1).isEqualTo(zoneTypeDTO2);
        zoneTypeDTO2.setId(2L);
        assertThat(zoneTypeDTO1).isNotEqualTo(zoneTypeDTO2);
        zoneTypeDTO1.setId(null);
        assertThat(zoneTypeDTO1).isNotEqualTo(zoneTypeDTO2);
    }
}
