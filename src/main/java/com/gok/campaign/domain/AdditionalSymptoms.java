package com.gok.campaign.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A AdditionalSymptoms.
 */
@Entity
@Table(name = "additional_symptoms")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdditionalSymptoms extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "identifier")
    private String identifier;

    @OneToMany(mappedBy = "additionalSymptoms")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CitizenMedical> citizenMedicals = new HashSet<>();

    @OneToMany(mappedBy = "additionalSymptoms")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ServiceReq> serviceReqs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public AdditionalSymptoms identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Set<CitizenMedical> getCitizenMedicals() {
        return citizenMedicals;
    }

    public AdditionalSymptoms citizenMedicals(Set<CitizenMedical> citizenMedicals) {
        this.citizenMedicals = citizenMedicals;
        return this;
    }

    public AdditionalSymptoms addCitizenMedical(CitizenMedical citizenMedical) {
        this.citizenMedicals.add(citizenMedical);
        citizenMedical.setAdditionalSymptoms(this);
        return this;
    }

    public AdditionalSymptoms removeCitizenMedical(CitizenMedical citizenMedical) {
        this.citizenMedicals.remove(citizenMedical);
        citizenMedical.setAdditionalSymptoms(null);
        return this;
    }

    public void setCitizenMedicals(Set<CitizenMedical> citizenMedicals) {
        this.citizenMedicals = citizenMedicals;
    }

    public Set<ServiceReq> getServiceReqs() {
        return serviceReqs;
    }

    public AdditionalSymptoms serviceReqs(Set<ServiceReq> serviceReqs) {
        this.serviceReqs = serviceReqs;
        return this;
    }

    public AdditionalSymptoms addServiceReq(ServiceReq serviceReq) {
        this.serviceReqs.add(serviceReq);
        serviceReq.setAdditionalSymptoms(this);
        return this;
    }

    public AdditionalSymptoms removeServiceReq(ServiceReq serviceReq) {
        this.serviceReqs.remove(serviceReq);
        serviceReq.setAdditionalSymptoms(null);
        return this;
    }

    public void setServiceReqs(Set<ServiceReq> serviceReqs) {
        this.serviceReqs = serviceReqs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdditionalSymptoms)) {
            return false;
        }
        return id != null && id.equals(((AdditionalSymptoms) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdditionalSymptoms{" +
            "id=" + getId() +
            ", identifier='" + getIdentifier() + "'" +
            "}";
    }
}
