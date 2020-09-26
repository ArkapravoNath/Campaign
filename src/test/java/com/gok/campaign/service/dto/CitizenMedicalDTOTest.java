package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class CitizenMedicalDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CitizenMedicalDTO.class);
        CitizenMedicalDTO citizenMedicalDTO1 = new CitizenMedicalDTO();
        citizenMedicalDTO1.setId(1L);
        CitizenMedicalDTO citizenMedicalDTO2 = new CitizenMedicalDTO();
        assertThat(citizenMedicalDTO1).isNotEqualTo(citizenMedicalDTO2);
        citizenMedicalDTO2.setId(citizenMedicalDTO1.getId());
        assertThat(citizenMedicalDTO1).isEqualTo(citizenMedicalDTO2);
        citizenMedicalDTO2.setId(2L);
        assertThat(citizenMedicalDTO1).isNotEqualTo(citizenMedicalDTO2);
        citizenMedicalDTO1.setId(null);
        assertThat(citizenMedicalDTO1).isNotEqualTo(citizenMedicalDTO2);
    }
}
