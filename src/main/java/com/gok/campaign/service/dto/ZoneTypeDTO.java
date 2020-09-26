package com.gok.campaign.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.campaign.domain.ZoneType} entity.
 */
public class ZoneTypeDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String name;

    private String identifier;

    private String description;

    
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ZoneTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((ZoneTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ZoneTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
