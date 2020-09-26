package com.gok.campaign.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.campaign.domain.CitizenMedical} entity.
 */
public class CitizenMedicalDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String measurement;

    private String remark;


    private Long medicalSysmptomId;

    private Long additionalSymptomsId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getMedicalSysmptomId() {
        return medicalSysmptomId;
    }

    public void setMedicalSysmptomId(Long medicalSymptomId) {
        this.medicalSysmptomId = medicalSymptomId;
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
        if (!(o instanceof CitizenMedicalDTO)) {
            return false;
        }

        return id != null && id.equals(((CitizenMedicalDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CitizenMedicalDTO{" +
            "id=" + getId() +
            ", measurement='" + getMeasurement() + "'" +
            ", remark='" + getRemark() + "'" +
            ", medicalSysmptomId=" + getMedicalSysmptomId() +
            ", additionalSymptomsId=" + getAdditionalSymptomsId() +
            "}";
    }
}
