package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class EssentialsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EssentialsDTO.class);
        EssentialsDTO essentialsDTO1 = new EssentialsDTO();
        essentialsDTO1.setId(1L);
        EssentialsDTO essentialsDTO2 = new EssentialsDTO();
        assertThat(essentialsDTO1).isNotEqualTo(essentialsDTO2);
        essentialsDTO2.setId(essentialsDTO1.getId());
        assertThat(essentialsDTO1).isEqualTo(essentialsDTO2);
        essentialsDTO2.setId(2L);
        assertThat(essentialsDTO1).isNotEqualTo(essentialsDTO2);
        essentialsDTO1.setId(null);
        assertThat(essentialsDTO1).isNotEqualTo(essentialsDTO2);
    }
}
