package com.gok.campaign.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.campaign.domain.File} entity.
 */
public class FileDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String fileName;

    private String type;

    private Float size;

    private String fileOriginalName;

    private String fileLocation;

    private String antivirusCheck;

    private String contentType;

    private String campaignCode;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public String getFileOriginalName() {
        return fileOriginalName;
    }

    public void setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getAntivirusCheck() {
        return antivirusCheck;
    }

    public void setAntivirusCheck(String antivirusCheck) {
        this.antivirusCheck = antivirusCheck;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileDTO)) {
            return false;
        }

        return id != null && id.equals(((FileDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FileDTO{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", type='" + getType() + "'" +
            ", size=" + getSize() +
            ", fileOriginalName='" + getFileOriginalName() + "'" +
            ", fileLocation='" + getFileLocation() + "'" +
            ", antivirusCheck='" + getAntivirusCheck() + "'" +
            ", contentType='" + getContentType() + "'" +
            ", campaignCode='" + getCampaignCode() + "'" +
            "}";
    }
}
