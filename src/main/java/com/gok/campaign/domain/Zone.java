package com.gok.campaign.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.gok.campaign.domain.enumeration.StatusType;

/**
 * A Zone.
 */
@Entity
@Table(name = "zone")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Zone extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @Column(name = "radius")
    private Float radius;

    @Column(name = "metadata")
    private String metadata;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "zone_zone_type",
               joinColumns = @JoinColumn(name = "zone_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "zone_type_id", referencedColumnName = "id"))
    private Set<ZoneType> zoneTypes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "zone_location",
               joinColumns = @JoinColumn(name = "zone_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id"))
    private Set<Location> locations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Zone name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Zone code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public Zone description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusType getStatus() {
        return status;
    }

    public Zone status(StatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Float getRadius() {
        return radius;
    }

    public Zone radius(Float radius) {
        this.radius = radius;
        return this;
    }

    public void setRadius(Float radius) {
        this.radius = radius;
    }

    public String getMetadata() {
        return metadata;
    }

    public Zone metadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Set<ZoneType> getZoneTypes() {
        return zoneTypes;
    }

    public Zone zoneTypes(Set<ZoneType> zoneTypes) {
        this.zoneTypes = zoneTypes;
        return this;
    }

    public Zone addZoneType(ZoneType zoneType) {
        this.zoneTypes.add(zoneType);
        zoneType.getZones().add(this);
        return this;
    }

    public Zone removeZoneType(ZoneType zoneType) {
        this.zoneTypes.remove(zoneType);
        zoneType.getZones().remove(this);
        return this;
    }

    public void setZoneTypes(Set<ZoneType> zoneTypes) {
        this.zoneTypes = zoneTypes;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public Zone locations(Set<Location> locations) {
        this.locations = locations;
        return this;
    }

    public Zone addLocation(Location location) {
        this.locations.add(location);
        location.getZones().add(this);
        return this;
    }

    public Zone removeLocation(Location location) {
        this.locations.remove(location);
        location.getZones().remove(this);
        return this;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Zone)) {
            return false;
        }
        return id != null && id.equals(((Zone) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Zone{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", radius=" + getRadius() +
            ", metadata='" + getMetadata() + "'" +
            "}";
    }
}
