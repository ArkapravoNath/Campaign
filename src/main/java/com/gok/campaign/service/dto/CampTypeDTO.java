package com.gok.campaign.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.campaign.domain.CampType} entity.
 */
public class CampTypeDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String name;

    private String metadata;

    
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
        if (!(o instanceof CampTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((CampTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CampTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", metadata='" + getMetadata() + "'" +
            "}";
    }
}
