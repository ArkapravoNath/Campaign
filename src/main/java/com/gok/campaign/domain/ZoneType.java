package com.gok.campaign.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ZoneType.
 */
@Entity
@Table(name = "zone_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZoneType extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "zoneTypes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Zone> zones = new HashSet<>();

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

    public ZoneType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ZoneType identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public ZoneType description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Zone> getZones() {
        return zones;
    }

    public ZoneType zones(Set<Zone> zones) {
        this.zones = zones;
        return this;
    }

    public ZoneType addZone(Zone zone) {
        this.zones.add(zone);
        zone.getZoneTypes().add(this);
        return this;
    }

    public ZoneType removeZone(Zone zone) {
        this.zones.remove(zone);
        zone.getZoneTypes().remove(this);
        return this;
    }

    public void setZones(Set<Zone> zones) {
        this.zones = zones;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ZoneType)) {
            return false;
        }
        return id != null && id.equals(((ZoneType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ZoneType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
