package com.gok.campaign.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Location.
 */
@Entity
@Table(name = "location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Location extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "taluka")
    private String taluka;

    @Column(name = "location")
    private String location;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "street")
    private String street;

    @Column(name = "area")
    private String area;

    @Column(name = "house_no")
    private String houseNo;

    @ManyToMany(mappedBy = "locations")
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

    public String getCity() {
        return city;
    }

    public Location city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTaluka() {
        return taluka;
    }

    public Location taluka(String taluka) {
        this.taluka = taluka;
        return this;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getLocation() {
        return location;
    }

    public Location location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPinCode() {
        return pinCode;
    }

    public Location pinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Location latitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Location longitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public Location street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public Location area(String area) {
        this.area = area;
        return this;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public Location houseNo(String houseNo) {
        this.houseNo = houseNo;
        return this;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public Set<Zone> getZones() {
        return zones;
    }

    public Location zones(Set<Zone> zones) {
        this.zones = zones;
        return this;
    }

    public Location addZone(Zone zone) {
        this.zones.add(zone);
        zone.getLocations().add(this);
        return this;
    }

    public Location removeZone(Zone zone) {
        this.zones.remove(zone);
        zone.getLocations().remove(this);
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
        if (!(o instanceof Location)) {
            return false;
        }
        return id != null && id.equals(((Location) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Location{" +
            "id=" + getId() +
            ", city='" + getCity() + "'" +
            ", taluka='" + getTaluka() + "'" +
            ", location='" + getLocation() + "'" +
            ", pinCode='" + getPinCode() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", street='" + getStreet() + "'" +
            ", area='" + getArea() + "'" +
            ", houseNo='" + getHouseNo() + "'" +
            "}";
    }
}
