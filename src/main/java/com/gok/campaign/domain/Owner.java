package com.gok.campaign.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Owner.
 */
@Entity
@Table(name = "owner")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Owner extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "command_center_number")
    private String commandCenterNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "metadata")
    private String metadata;

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

    public Owner name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public Owner number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCommandCenterNumber() {
        return commandCenterNumber;
    }

    public Owner commandCenterNumber(String commandCenterNumber) {
        this.commandCenterNumber = commandCenterNumber;
        return this;
    }

    public void setCommandCenterNumber(String commandCenterNumber) {
        this.commandCenterNumber = commandCenterNumber;
    }

    public String getEmail() {
        return email;
    }

    public Owner email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMetadata() {
        return metadata;
    }

    public Owner metadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Owner)) {
            return false;
        }
        return id != null && id.equals(((Owner) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Owner{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", number='" + getNumber() + "'" +
            ", commandCenterNumber='" + getCommandCenterNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", metadata='" + getMetadata() + "'" +
            "}";
    }
}
