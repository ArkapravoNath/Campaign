package com.gok.campaign.service.dto;

import java.io.Serializable;
import com.gok.campaign.domain.enumeration.ServiceType;
import com.gok.campaign.domain.enumeration.ServiceStatus;
import com.gok.campaign.domain.enumeration.ServiceSubStatus;

/**
 * A DTO for the {@link com.gok.campaign.domain.ServiceReq} entity.
 */
public class ServiceReqDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private ServiceType serviceType;

    private String name;

    private String description;

    private ServiceStatus status;

    private String statusDescription;

    private ServiceSubStatus subStatus;

    private String remarks;


    private Long additionalSymptomsId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public ServiceSubStatus getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(ServiceSubStatus subStatus) {
        this.subStatus = subStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getAdditionalSymptomsId() {
        return additionalSymptomsId;
    }

    public void setAdditionalSymptomsId(Long additionalSymptomsId) {
        this.additionalSymptomsId = additionalSymptomsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceReqDTO)) {
            return false;
        }

        return id != null && id.equals(((ServiceReqDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceReqDTO{" +
            "id=" + getId() +
            ", serviceType='" + getServiceType() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusDescription='" + getStatusDescription() + "'" +
            ", subStatus='" + getSubStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", additionalSymptomsId=" + getAdditionalSymptomsId() +
            "}";
    }
}
