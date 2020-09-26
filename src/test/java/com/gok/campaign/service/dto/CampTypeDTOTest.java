package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class CampTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampTypeDTO.class);
        CampTypeDTO campTypeDTO1 = new CampTypeDTO();
        campTypeDTO1.setId(1L);
        CampTypeDTO campTypeDTO2 = new CampTypeDTO();
        assertThat(campTypeDTO1).isNotEqualTo(campTypeDTO2);
        campTypeDTO2.setId(campTypeDTO1.getId());
        assertThat(campTypeDTO1).isEqualTo(campTypeDTO2);
        campTypeDTO2.setId(2L);
        assertThat(campTypeDTO1).isNotEqualTo(campTypeDTO2);
        campTypeDTO1.setId(null);
        assertThat(campTypeDTO1).isNotEqualTo(campTypeDTO2);
    }
}
