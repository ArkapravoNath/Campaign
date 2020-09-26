package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class ColumnMasterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ColumnMasterDTO.class);
        ColumnMasterDTO columnMasterDTO1 = new ColumnMasterDTO();
        columnMasterDTO1.setId(1L);
        ColumnMasterDTO columnMasterDTO2 = new ColumnMasterDTO();
        assertThat(columnMasterDTO1).isNotEqualTo(columnMasterDTO2);
        columnMasterDTO2.setId(columnMasterDTO1.getId());
        assertThat(columnMasterDTO1).isEqualTo(columnMasterDTO2);
        columnMasterDTO2.setId(2L);
        assertThat(columnMasterDTO1).isNotEqualTo(columnMasterDTO2);
        columnMasterDTO1.setId(null);
        assertThat(columnMasterDTO1).isNotEqualTo(columnMasterDTO2);
    }
}
