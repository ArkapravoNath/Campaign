package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class CampTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampType.class);
        CampType campType1 = new CampType();
        campType1.setId(1L);
        CampType campType2 = new CampType();
        campType2.setId(campType1.getId());
        assertThat(campType1).isEqualTo(campType2);
        campType2.setId(2L);
        assertThat(campType1).isNotEqualTo(campType2);
        campType1.setId(null);
        assertThat(campType1).isNotEqualTo(campType2);
    }
}
