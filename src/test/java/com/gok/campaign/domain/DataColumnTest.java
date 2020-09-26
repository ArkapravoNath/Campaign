package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class DataColumnTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataColumn.class);
        DataColumn dataColumn1 = new DataColumn();
        dataColumn1.setId(1L);
        DataColumn dataColumn2 = new DataColumn();
        dataColumn2.setId(dataColumn1.getId());
        assertThat(dataColumn1).isEqualTo(dataColumn2);
        dataColumn2.setId(2L);
        assertThat(dataColumn1).isNotEqualTo(dataColumn2);
        dataColumn1.setId(null);
        assertThat(dataColumn1).isNotEqualTo(dataColumn2);
    }
}
