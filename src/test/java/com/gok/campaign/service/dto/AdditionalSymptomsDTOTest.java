package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class AdditionalSymptomsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdditionalSymptomsDTO.class);
        AdditionalSymptomsDTO additionalSymptomsDTO1 = new AdditionalSymptomsDTO();
        additionalSymptomsDTO1.setId(1L);
        AdditionalSymptomsDTO additionalSymptomsDTO2 = new AdditionalSymptomsDTO();
        assertThat(additionalSymptomsDTO1).isNotEqualTo(additionalSymptomsDTO2);
        additionalSymptomsDTO2.setId(additionalSymptomsDTO1.getId());
        assertThat(additionalSymptomsDTO1).isEqualTo(additionalSymptomsDTO2);
        additionalSymptomsDTO2.setId(2L);
        assertThat(additionalSymptomsDTO1).isNotEqualTo(additionalSymptomsDTO2);
        additionalSymptomsDTO1.setId(null);
        assertThat(additionalSymptomsDTO1).isNotEqualTo(additionalSymptomsDTO2);
    }
}
