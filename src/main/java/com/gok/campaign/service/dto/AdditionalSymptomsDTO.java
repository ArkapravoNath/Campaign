package com.gok.campaign.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.campaign.domain.AdditionalSymptoms} entity.
 */
public class AdditionalSymptomsDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String identifier;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdditionalSymptomsDTO)) {
            return false;
        }

        return id != null && id.equals(((AdditionalSymptomsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdditionalSymptomsDTO{" +
            "id=" + getId() +
            ", identifier='" + getIdentifier() + "'" +
            "}";
    }
}
