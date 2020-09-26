package com.gok.campaign.service.dto;

import java.io.Serializable;
import com.gok.campaign.domain.enumeration.SourceType;

/**
 * A DTO for the {@link com.gok.campaign.domain.AnswerSet} entity.
 */
public class AnswerSetDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String name;

    private SourceType source;

    private String metadata;

    private String remarks;


    private Long fileId;

    private Long questionSetId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SourceType getSource() {
        return source;
    }

    public void setSource(SourceType source) {
        this.source = source;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(Long questionSetId) {
        this.questionSetId = questionSetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerSetDTO)) {
            return false;
        }

        return id != null && id.equals(((AnswerSetDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerSetDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", source='" + getSource() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", fileId=" + getFileId() +
            ", questionSetId=" + getQuestionSetId() +
            "}";
    }
}
