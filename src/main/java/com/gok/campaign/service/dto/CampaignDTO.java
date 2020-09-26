package com.gok.campaign.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.gok.campaign.domain.enumeration.StatusType;

/**
 * A DTO for the {@link com.gok.campaign.domain.Campaign} entity.
 */
public class CampaignDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String name;

    @NotNull
    private String code;

    private StatusType status;

    private Instant startDate;

    private Instant endDate;

    private String metadata;

    private Boolean manipulateSymptom;


    private Long parentId;

    private Long ownerId;

    private Long locationId;

    private Long zoneId;

    private Long questionSetId;

    private Long typeId;
    
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Boolean isManipulateSymptom() {
        return manipulateSymptom;
    }

    public void setManipulateSymptom(Boolean manipulateSymptom) {
        this.manipulateSymptom = manipulateSymptom;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long campaignId) {
        this.parentId = campaignId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public Long getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(Long questionSetId) {
        this.questionSetId = questionSetId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long campTypeId) {
        this.typeId = campTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CampaignDTO)) {
            return false;
        }

        return id != null && id.equals(((CampaignDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CampaignDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", manipulateSymptom='" + isManipulateSymptom() + "'" +
            ", parentId=" + getParentId() +
            ", ownerId=" + getOwnerId() +
            ", locationId=" + getLocationId() +
            ", zoneId=" + getZoneId() +
            ", questionSetId=" + getQuestionSetId() +
            ", typeId=" + getTypeId() +
            "}";
    }
}
