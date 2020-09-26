package com.gok.campaign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class FileParseRequestTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileParseRequest.class);
        FileParseRequest fileParseRequest1 = new FileParseRequest();
        fileParseRequest1.setId(1L);
        FileParseRequest fileParseRequest2 = new FileParseRequest();
        fileParseRequest2.setId(fileParseRequest1.getId());
        assertThat(fileParseRequest1).isEqualTo(fileParseRequest2);
        fileParseRequest2.setId(2L);
        assertThat(fileParseRequest1).isNotEqualTo(fileParseRequest2);
        fileParseRequest1.setId(null);
        assertThat(fileParseRequest1).isNotEqualTo(fileParseRequest2);
    }
}
