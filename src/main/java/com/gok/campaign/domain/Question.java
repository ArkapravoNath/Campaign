package com.gok.campaign.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.gok.campaign.domain.enumeration.StatusType;

/**
 * A Question.
 */
@Entity
@Table(name = "question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Question extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "input_control")
    private String inputControl;

    @Column(name = "master_values")
    private String masterValues;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @ManyToMany(mappedBy = "questions")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<QuestionSet> questionSets = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Question text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInputControl() {
        return inputControl;
    }

    public Question inputControl(String inputControl) {
        this.inputControl = inputControl;
        return this;
    }

    public void setInputControl(String inputControl) {
        this.inputControl = inputControl;
    }

    public String getMasterValues() {
        return masterValues;
    }

    public Question masterValues(String masterValues) {
        this.masterValues = masterValues;
        return this;
    }

    public void setMasterValues(String masterValues) {
        this.masterValues = masterValues;
    }

    public StatusType getStatus() {
        return status;
    }

    public Question status(StatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Set<QuestionSet> getQuestionSets() {
        return questionSets;
    }

    public Question questionSets(Set<QuestionSet> questionSets) {
        this.questionSets = questionSets;
        return this;
    }

    public Question addQuestionSet(QuestionSet questionSet) {
        this.questionSets.add(questionSet);
        questionSet.getQuestions().add(this);
        return this;
    }

    public Question removeQuestionSet(QuestionSet questionSet) {
        this.questionSets.remove(questionSet);
        questionSet.getQuestions().remove(this);
        return this;
    }

    public void setQuestionSets(Set<QuestionSet> questionSets) {
        this.questionSets = questionSets;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        return id != null && id.equals(((Question) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Question{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", inputControl='" + getInputControl() + "'" +
            ", masterValues='" + getMasterValues() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
