package com.gok.campaign.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.campaign.domain.Owner} entity.
 */
public class OwnerDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String name;

    private String number;

    private String commandCenterNumber;

    private String email;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCommandCenterNumber() {
        return commandCenterNumber;
    }

    public void setCommandCenterNumber(String commandCenterNumber) {
        this.commandCenterNumber = commandCenterNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(o instanceof OwnerDTO)) {
            return false;
        }

        return id != null && id.equals(((OwnerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OwnerDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", number='" + getNumber() + "'" +
            ", commandCenterNumber='" + getCommandCenterNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", metadata='" + getMetadata() + "'" +
            "}";
    }
}
