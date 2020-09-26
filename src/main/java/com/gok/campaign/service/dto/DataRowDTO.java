package com.gok.campaign.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.campaign.domain.DataRow} entity.
 */
public class DataRowDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String sNo;

    private String identifier;

    private String source;


    private Long fileId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataRowDTO)) {
            return false;
        }

        return id != null && id.equals(((DataRowDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataRowDTO{" +
            "id=" + getId() +
            ", sNo='" + getsNo() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", source='" + getSource() + "'" +
            ", fileId=" + getFileId() +
            "}";
    }
}
