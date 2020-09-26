package com.gok.campaign.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import com.gok.campaign.domain.enumeration.RequestStatus;

/**
 * A FileParseRequest.
 */
@Entity
@Table(name = "file_parse_request")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileParseRequest extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "file_name")
    private String fileName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RequestStatus status;

    @Column(name = "message")
    private String message;

    @Column(name = "records")
    private Long records;

    @Column(name = "metadata")
    private String metadata;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileId() {
        return fileId;
    }

    public FileParseRequest fileId(Long fileId) {
        this.fileId = fileId;
        return this;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public FileParseRequest fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public FileParseRequest status(RequestStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public FileParseRequest message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getRecords() {
        return records;
    }

    public FileParseRequest records(Long records) {
        this.records = records;
        return this;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public String getMetadata() {
        return metadata;
    }

    public FileParseRequest metadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileParseRequest)) {
            return false;
        }
        return id != null && id.equals(((FileParseRequest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FileParseRequest{" +
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
