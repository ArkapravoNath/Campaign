package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class EssentialsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Essentials.class);
        Essentials essentials1 = new Essentials();
        essentials1.setId(1L);
        Essentials essentials2 = new Essentials();
        essentials2.setId(essentials1.getId());
        assertThat(essentials1).isEqualTo(essentials2);
        essentials2.setId(2L);
        assertThat(essentials1).isNotEqualTo(essentials2);
        essentials1.setId(null);
        assertThat(essentials1).isNotEqualTo(essentials2);
    }
}
