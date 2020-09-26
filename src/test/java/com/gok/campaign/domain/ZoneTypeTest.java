package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class ZoneTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ZoneType.class);
        ZoneType zoneType1 = new ZoneType();
        zoneType1.setId(1L);
        ZoneType zoneType2 = new ZoneType();
        zoneType2.setId(zoneType1.getId());
        assertThat(zoneType1).isEqualTo(zoneType2);
        zoneType2.setId(2L);
        assertThat(zoneType1).isNotEqualTo(zoneType2);
        zoneType1.setId(null);
        assertThat(zoneType1).isNotEqualTo(zoneType2);
    }
}
