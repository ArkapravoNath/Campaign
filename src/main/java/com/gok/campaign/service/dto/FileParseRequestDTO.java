package com.gok.campaign.service.dto;

import java.io.Serializable;
import com.gok.campaign.domain.enumeration.RequestStatus;

/**
 * A DTO for the {@link com.gok.campaign.domain.FileParseRequest} entity.
 */
public class FileParseRequestDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private Long fileId;

    private String fileName;

    private RequestStatus status;

    private String message;

    private Long records;

    private String metadata;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileParseRequestDTO)) {
            return false;
        }

        return id != null && id.equals(((FileParseRequestDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FileParseRequestDTO{" +
            "id=" + getId() +
            ", fileId=" + getFileId() +
            ", fileName='" + getFileName() + "'" +
            ", status='" + getStatus() + "'" +
            ", message='" + getMessage() + "'" +
            ", records=" + getRecords() +
            ", metadata='" + getMetadata() + "'" +
            "}";
    }
}
