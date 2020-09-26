package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class CitizenMedicalTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CitizenMedical.class);
        CitizenMedical citizenMedical1 = new CitizenMedical();
        citizenMedical1.setId(1L);
        CitizenMedical citizenMedical2 = new CitizenMedical();
        citizenMedical2.setId(citizenMedical1.getId());
        assertThat(citizenMedical1).isEqualTo(citizenMedical2);
        citizenMedical2.setId(2L);
        assertThat(citizenMedical1).isNotEqualTo(citizenMedical2);
        citizenMedical1.setId(null);
        assertThat(citizenMedical1).isNotEqualTo(citizenMedical2);
    }
}
