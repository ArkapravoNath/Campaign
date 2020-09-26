package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class ColumnMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ColumnMaster.class);
        ColumnMaster columnMaster1 = new ColumnMaster();
        columnMaster1.setId(1L);
        ColumnMaster columnMaster2 = new ColumnMaster();
        columnMaster2.setId(columnMaster1.getId());
        assertThat(columnMaster1).isEqualTo(columnMaster2);
        columnMaster2.setId(2L);
        assertThat(columnMaster1).isNotEqualTo(columnMaster2);
        columnMaster1.setId(null);
        assertThat(columnMaster1).isNotEqualTo(columnMaster2);
    }
}
