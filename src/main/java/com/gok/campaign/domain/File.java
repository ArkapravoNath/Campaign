package com.gok.campaign.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A File.
 */
@Entity
@Table(name = "file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class File extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private Float size;

    @Column(name = "file_original_name")
    private String fileOriginalName;

    @Column(name = "file_location")
    private String fileLocation;

    @Column(name = "antivirus_check")
    private String antivirusCheck;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "campaign_code")
    private String campaignCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public File fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public File type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getSize() {
        return size;
    }

    public File size(Float size) {
        this.size = size;
        return this;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public String getFileOriginalName() {
        return fileOriginalName;
    }

    public File fileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;
        return this;
    }

    public void setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public File fileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
        return this;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getAntivirusCheck() {
        return antivirusCheck;
    }

    public File antivirusCheck(String antivirusCheck) {
        this.antivirusCheck = antivirusCheck;
        return this;
    }

    public void setAntivirusCheck(String antivirusCheck) {
        this.antivirusCheck = antivirusCheck;
    }

    public String getContentType() {
        return contentType;
    }

    public File contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public File campaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
        return this;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof File)) {
            return false;
        }
        return id != null && id.equals(((File) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "File{" +
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
