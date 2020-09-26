package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class AdditionalSymptomsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdditionalSymptoms.class);
        AdditionalSymptoms additionalSymptoms1 = new AdditionalSymptoms();
        additionalSymptoms1.setId(1L);
        AdditionalSymptoms additionalSymptoms2 = new AdditionalSymptoms();
        additionalSymptoms2.setId(additionalSymptoms1.getId());
        assertThat(additionalSymptoms1).isEqualTo(additionalSymptoms2);
        additionalSymptoms2.setId(2L);
        assertThat(additionalSymptoms1).isNotEqualTo(additionalSymptoms2);
        additionalSymptoms1.setId(null);
        assertThat(additionalSymptoms1).isNotEqualTo(additionalSymptoms2);
    }
}
