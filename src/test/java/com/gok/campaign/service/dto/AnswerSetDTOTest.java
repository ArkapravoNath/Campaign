package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class AnswerSetDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerSetDTO.class);
        AnswerSetDTO answerSetDTO1 = new AnswerSetDTO();
        answerSetDTO1.setId(1L);
        AnswerSetDTO answerSetDTO2 = new AnswerSetDTO();
        assertThat(answerSetDTO1).isNotEqualTo(answerSetDTO2);
        answerSetDTO2.setId(answerSetDTO1.getId());
        assertThat(answerSetDTO1).isEqualTo(answerSetDTO2);
        answerSetDTO2.setId(2L);
        assertThat(answerSetDTO1).isNotEqualTo(answerSetDTO2);
        answerSetDTO1.setId(null);
        assertThat(answerSetDTO1).isNotEqualTo(answerSetDTO2);
    }
}
