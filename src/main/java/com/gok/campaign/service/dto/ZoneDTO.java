package com.gok.campaign.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.gok.campaign.domain.enumeration.StatusType;

/**
 * A DTO for the {@link com.gok.campaign.domain.Zone} entity.
 */
public class ZoneDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String name;

    private String code;

    private String description;

    private StatusType status;

    private Float radius;

    private String metadata;

    private Set<ZoneTypeDTO> zoneTypes = new HashSet<>();
    private Set<LocationDTO> locations = new HashSet<>();
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Float getRadius() {
        return radius;
    }

    public void setRadius(Float radius) {
        this.radius = radius;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Set<ZoneTypeDTO> getZoneTypes() {
        return zoneTypes;
    }

    public void setZoneTypes(Set<ZoneTypeDTO> zoneTypes) {
        this.zoneTypes = zoneTypes;
    }

    public Set<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(Set<LocationDTO> locations) {
        this.locations = locations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ZoneDTO)) {
            return false;
        }

        return id != null && id.equals(((ZoneDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ZoneDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", radius=" + getRadius() +
            ", metadata='" + getMetadata() + "'" +
            ", zoneTypes='" + getZoneTypes() + "'" +
            ", locations='" + getLocations() + "'" +
            "}";
    }
}
