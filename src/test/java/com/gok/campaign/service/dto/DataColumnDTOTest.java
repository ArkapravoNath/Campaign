package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class DataColumnDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataColumnDTO.class);
        DataColumnDTO dataColumnDTO1 = new DataColumnDTO();
        dataColumnDTO1.setId(1L);
        DataColumnDTO dataColumnDTO2 = new DataColumnDTO();
        assertThat(dataColumnDTO1).isNotEqualTo(dataColumnDTO2);
        dataColumnDTO2.setId(dataColumnDTO1.getId());
        assertThat(dataColumnDTO1).isEqualTo(dataColumnDTO2);
        dataColumnDTO2.setId(2L);
        assertThat(dataColumnDTO1).isNotEqualTo(dataColumnDTO2);
        dataColumnDTO1.setId(null);
        assertThat(dataColumnDTO1).isNotEqualTo(dataColumnDTO2);
    }
}
