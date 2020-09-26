package com.gok.campaign.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MedicalSymptom.
 */
@Entity
@Table(name = "medical_symptom")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MedicalSymptom extends AbstractAuditingEntity implements Serializable {

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

    @Column(name = "priority")
    private Integer priority;

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

    public MedicalSymptom name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public MedicalSymptom identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public MedicalSymptom description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public MedicalSymptom priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalSymptom)) {
            return false;
        }
        return id != null && id.equals(((MedicalSymptom) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalSymptom{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", description='" + getDescription() + "'" +
            ", priority=" + getPriority() +
            "}";
    }
}
