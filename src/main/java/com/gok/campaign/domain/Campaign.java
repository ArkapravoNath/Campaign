package com.gok.campaign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.gok.campaign.domain.enumeration.StatusType;

/**
 * A Campaign.
 */
@Entity
@Table(name = "campaign")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Campaign extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "metadata")
    private String metadata;

    @Column(name = "manipulate_symptom")
    private Boolean manipulateSymptom;

    @OneToOne
    @JoinColumn(unique = true)
    private Campaign parent;

    @OneToOne
    @JoinColumn(unique = true)
    private Owner owner;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    @OneToOne
    @JoinColumn(unique = true)
    private Zone zone;

    @OneToOne
    @JoinColumn(unique = true)
    private QuestionSet questionSet;

    @ManyToOne
    @JsonIgnoreProperties(value = "campaigns", allowSetters = true)
    private CampType type;

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

    public Campaign name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Campaign code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public StatusType getStatus() {
        return status;
    }

    public Campaign status(StatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Campaign startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Campaign endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getMetadata() {
        return metadata;
    }

    public Campaign metadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Boolean isManipulateSymptom() {
        return manipulateSymptom;
    }

    public Campaign manipulateSymptom(Boolean manipulateSymptom) {
        this.manipulateSymptom = manipulateSymptom;
        return this;
    }

    public void setManipulateSymptom(Boolean manipulateSymptom) {
        this.manipulateSymptom = manipulateSymptom;
    }

    public Campaign getParent() {
        return parent;
    }

    public Campaign parent(Campaign campaign) {
        this.parent = campaign;
        return this;
    }

    public void setParent(Campaign campaign) {
        this.parent = campaign;
    }

    public Owner getOwner() {
        return owner;
    }

    public Campaign owner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public Campaign location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Zone getZone() {
        return zone;
    }

    public Campaign zone(Zone zone) {
        this.zone = zone;
        return this;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public QuestionSet getQuestionSet() {
        return questionSet;
    }

    public Campaign questionSet(QuestionSet questionSet) {
        this.questionSet = questionSet;
        return this;
    }

    public void setQuestionSet(QuestionSet questionSet) {
        this.questionSet = questionSet;
    }

    public CampType getType() {
        return type;
    }

    public Campaign type(CampType campType) {
        this.type = campType;
        return this;
    }

    public void setType(CampType campType) {
        this.type = campType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Campaign)) {
            return false;
        }
        return id != null && id.equals(((Campaign) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Campaign{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", manipulateSymptom='" + isManipulateSymptom() + "'" +
            "}";
    }
}
