package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class AnswerSetTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerSet.class);
        AnswerSet answerSet1 = new AnswerSet();
        answerSet1.setId(1L);
        AnswerSet answerSet2 = new AnswerSet();
        answerSet2.setId(answerSet1.getId());
        assertThat(answerSet1).isEqualTo(answerSet2);
        answerSet2.setId(2L);
        assertThat(answerSet1).isNotEqualTo(answerSet2);
        answerSet1.setId(null);
        assertThat(answerSet1).isNotEqualTo(answerSet2);
    }
}
