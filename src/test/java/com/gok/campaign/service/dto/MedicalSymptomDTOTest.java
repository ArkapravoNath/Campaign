package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class MedicalSymptomDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalSymptomDTO.class);
        MedicalSymptomDTO medicalSymptomDTO1 = new MedicalSymptomDTO();
        medicalSymptomDTO1.setId(1L);
        MedicalSymptomDTO medicalSymptomDTO2 = new MedicalSymptomDTO();
        assertThat(medicalSymptomDTO1).isNotEqualTo(medicalSymptomDTO2);
        medicalSymptomDTO2.setId(medicalSymptomDTO1.getId());
        assertThat(medicalSymptomDTO1).isEqualTo(medicalSymptomDTO2);
        medicalSymptomDTO2.setId(2L);
        assertThat(medicalSymptomDTO1).isNotEqualTo(medicalSymptomDTO2);
        medicalSymptomDTO1.setId(null);
        assertThat(medicalSymptomDTO1).isNotEqualTo(medicalSymptomDTO2);
    }
}
