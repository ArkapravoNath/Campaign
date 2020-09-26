package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class QuestionSetDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSetDTO.class);
        QuestionSetDTO questionSetDTO1 = new QuestionSetDTO();
        questionSetDTO1.setId(1L);
        QuestionSetDTO questionSetDTO2 = new QuestionSetDTO();
        assertThat(questionSetDTO1).isNotEqualTo(questionSetDTO2);
        questionSetDTO2.setId(questionSetDTO1.getId());
        assertThat(questionSetDTO1).isEqualTo(questionSetDTO2);
        questionSetDTO2.setId(2L);
        assertThat(questionSetDTO1).isNotEqualTo(questionSetDTO2);
        questionSetDTO1.setId(null);
        assertThat(questionSetDTO1).isNotEqualTo(questionSetDTO2);
    }
}
