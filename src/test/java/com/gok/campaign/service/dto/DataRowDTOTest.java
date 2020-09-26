package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class DataRowDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataRowDTO.class);
        DataRowDTO dataRowDTO1 = new DataRowDTO();
        dataRowDTO1.setId(1L);
        DataRowDTO dataRowDTO2 = new DataRowDTO();
        assertThat(dataRowDTO1).isNotEqualTo(dataRowDTO2);
        dataRowDTO2.setId(dataRowDTO1.getId());
        assertThat(dataRowDTO1).isEqualTo(dataRowDTO2);
        dataRowDTO2.setId(2L);
        assertThat(dataRowDTO1).isNotEqualTo(dataRowDTO2);
        dataRowDTO1.setId(null);
        assertThat(dataRowDTO1).isNotEqualTo(dataRowDTO2);
    }
}
