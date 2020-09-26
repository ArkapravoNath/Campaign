package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class DataRowTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataRow.class);
        DataRow dataRow1 = new DataRow();
        dataRow1.setId(1L);
        DataRow dataRow2 = new DataRow();
        dataRow2.setId(dataRow1.getId());
        assertThat(dataRow1).isEqualTo(dataRow2);
        dataRow2.setId(2L);
        assertThat(dataRow1).isNotEqualTo(dataRow2);
        dataRow1.setId(null);
        assertThat(dataRow1).isNotEqualTo(dataRow2);
    }
}
