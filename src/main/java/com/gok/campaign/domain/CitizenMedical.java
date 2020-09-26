package com.gok.campaign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CitizenMedical.
 */
@Entity
@Table(name = "citizen_medical")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CitizenMedical extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "measurement")
    private String measurement;

    @Column(name = "remark")
    private String remark;

    @ManyToOne
    @JsonIgnoreProperties(value = "citizenMedicals", allowSetters = true)
    private MedicalSymptom medicalSysmptom;

    @ManyToOne
    @JsonIgnoreProperties(value = "citizenMedicals", allowSetters = true)
    private AdditionalSymptoms additionalSymptoms;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeasurement() {
        return measurement;
    }

    public CitizenMedical measurement(String measurement) {
        this.measurement = measurement;
        return this;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getRemark() {
        return remark;
    }

    public CitizenMedical remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public MedicalSymptom getMedicalSysmptom() {
        return medicalSysmptom;
    }

    public CitizenMedical medicalSysmptom(MedicalSymptom medicalSymptom) {
        this.medicalSysmptom = medicalSymptom;
        return this;
    }

    public void setMedicalSysmptom(MedicalSymptom medicalSymptom) {
        this.medicalSysmptom = medicalSymptom;
    }

    public AdditionalSymptoms getAdditionalSymptoms() {
        return additionalSymptoms;
    }

    public CitizenMedical additionalSymptoms(AdditionalSymptoms additionalSymptoms) {
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
        if (!(o instanceof CitizenMedical)) {
            return false;
        }
        return id != null && id.equals(((CitizenMedical) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CitizenMedical{" +
            "id=" + getId() +
            ", measurement='" + getMeasurement() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
