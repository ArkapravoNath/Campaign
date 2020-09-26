package com.gok.campaign.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.campaign.web.rest.TestUtil;

public class FileParseRequestDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileParseRequestDTO.class);
        FileParseRequestDTO fileParseRequestDTO1 = new FileParseRequestDTO();
        fileParseRequestDTO1.setId(1L);
        FileParseRequestDTO fileParseRequestDTO2 = new FileParseRequestDTO();
        assertThat(fileParseRequestDTO1).isNotEqualTo(fileParseRequestDTO2);
        fileParseRequestDTO2.setId(fileParseRequestDTO1.getId());
        assertThat(fileParseRequestDTO1).isEqualTo(fileParseRequestDTO2);
        fileParseRequestDTO2.setId(2L);
        assertThat(fileParseRequestDTO1).isNotEqualTo(fileParseRequestDTO2);
        fileParseRequestDTO1.setId(null);
        assertThat(fileParseRequestDTO1).isNotEqualTo(fileParseRequestDTO2);
    }
}
