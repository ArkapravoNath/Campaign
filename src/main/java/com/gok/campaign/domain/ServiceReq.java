package com.gok.campaign.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import com.gok.campaign.domain.enumeration.ServiceType;

import com.gok.campaign.domain.enumeration.ServiceStatus;

import com.gok.campaign.domain.enumeration.ServiceSubStatus;

/**
 * A ServiceReq.
 */
@Entity
@Table(name = "service_req")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServiceReq extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type")
    private ServiceType serviceType;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ServiceStatus status;

    @Column(name = "status_description")
    private String statusDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "sub_status")
    private ServiceSubStatus subStatus;

    @Column(name = "remarks")
    private String remarks;

    @OneToOne(mappedBy = "serviceReq")
    @JsonIgnore
    private Essentials essentials;

    @ManyToOne
    @JsonIgnoreProperties(value = "serviceReqs", allowSetters = true)
    private AdditionalSymptoms additionalSymptoms;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public ServiceReq serviceType(ServiceType serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public ServiceReq name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public ServiceReq description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public ServiceReq status(ServiceStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public ServiceReq statusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
        return this;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public ServiceSubStatus getSubStatus() {
        return subStatus;
    }

    public ServiceReq subStatus(ServiceSubStatus subStatus) {
        this.subStatus = subStatus;
        return this;
    }

    public void setSubStatus(ServiceSubStatus subStatus) {
        this.subStatus = subStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public ServiceReq remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Essentials getEssentials() {
        return essentials;
    }

    public ServiceReq essentials(Essentials essentials) {
        this.essentials = essentials;
        return this;
    }

    public void setEssentials(Essentials essentials) {
        this.essentials = essentials;
    }

    public AdditionalSymptoms getAdditionalSymptoms() {
        return additionalSymptoms;
    }

    public ServiceReq additionalSymptoms(AdditionalSymptoms additionalSymptoms) {
        this.additionalSymptoms = additionalSymptoms;
        return this;
    }

    public void setAdditionalSymptoms(AdditionalSymptoms additionalSymptoms) {
        this.additionalSymptoms = additionalSymptoms;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceReq)) {
            return false;
        }
        return id != null && id.equals(((ServiceReq) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceReq{" +
            "id=" + getId() +
            ", serviceType='" + getServiceType() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusDescription='" + getStatusDescription() + "'" +
            ", subStatus='" + getSubStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
