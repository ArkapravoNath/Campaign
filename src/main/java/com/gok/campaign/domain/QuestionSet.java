package com.gok.campaign.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A QuestionSet.
 */
@Entity
@Table(name = "question_set")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuestionSet extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "metadata")
    private String metadata;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "question_set_question",
               joinColumns = @JoinColumn(name = "question_set_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Set<Question> questions = new HashSet<>();

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

    public QuestionSet name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetadata() {
        return metadata;
    }

    public QuestionSet metadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public QuestionSet questions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    public QuestionSet addQuestion(Question question) {
        this.questions.add(question);
        question.getQuestionSets().add(this);
        return this;
    }

    public QuestionSet removeQuestion(Question question) {
        this.questions.remove(question);
        question.getQuestionSets().remove(this);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSet)) {
            return false;
        }
        return id != null && id.equals(((QuestionSet) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSet{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", metadata='" + getMetadata() + "'" +
            "}";
    }
}
