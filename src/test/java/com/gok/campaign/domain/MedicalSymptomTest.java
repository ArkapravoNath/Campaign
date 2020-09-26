package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class MedicalSymptomTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalSymptom.class);
        MedicalSymptom medicalSymptom1 = new MedicalSymptom();
        medicalSymptom1.setId(1L);
        MedicalSymptom medicalSymptom2 = new MedicalSymptom();
        medicalSymptom2.setId(medicalSymptom1.getId());
        assertThat(medicalSymptom1).isEqualTo(medicalSymptom2);
        medicalSymptom2.setId(2L);
        assertThat(medicalSymptom1).isNotEqualTo(medicalSymptom2);
        medicalSymptom1.setId(null);
        assertThat(medicalSymptom1).isNotEqualTo(medicalSymptom2);
    }
}
