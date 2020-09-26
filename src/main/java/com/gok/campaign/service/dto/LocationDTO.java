package com.gok.campaign.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.campaign.domain.Location} entity.
 */
public class LocationDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String city;

    private String taluka;

    private String location;

    private String pinCode;

    private Float latitude;

    private Float longitude;

    private String street;

    private String area;

    private String houseNo;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocationDTO)) {
            return false;
        }

        return id != null && id.equals(((LocationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LocationDTO{" +
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
