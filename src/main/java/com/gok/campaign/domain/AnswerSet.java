package com.gok.campaign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.gok.campaign.domain.enumeration.SourceType;

/**
 * A AnswerSet.
 */
@Entity
@Table(name = "answer_set")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnswerSet extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "source")
    private SourceType source;

    @Column(name = "metadata")
    private String metadata;

    @Column(name = "remarks")
    private String remarks;

    @OneToMany(mappedBy = "answerSet")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Answer> answers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "answerSets", allowSetters = true)
    private File file;

    @ManyToOne
    @JsonIgnoreProperties(value = "answerSets", allowSetters = true)
    private QuestionSet questionSet;

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

    public AnswerSet name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SourceType getSource() {
        return source;
    }

    public AnswerSet source(SourceType source) {
        this.source = source;
        return this;
    }

    public void setSource(SourceType source) {
        this.source = source;
    }

    public String getMetadata() {
        return metadata;
    }

    public AnswerSet metadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getRemarks() {
        return remarks;
    }

    public AnswerSet remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public AnswerSet answers(Set<Answer> answers) {
        this.answers = answers;
        return this;
    }

    public AnswerSet addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.setAnswerSet(this);
        return this;
    }

    public AnswerSet removeAnswer(Answer answer) {
        this.answers.remove(answer);
        answer.setAnswerSet(null);
        return this;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public File getFile() {
        return file;
    }

    public AnswerSet file(File file) {
        this.file = file;
        return this;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public QuestionSet getQuestionSet() {
        return questionSet;
    }

    public AnswerSet questionSet(QuestionSet questionSet) {
        this.questionSet = questionSet;
        return this;
    }

    public void setQuestionSet(QuestionSet questionSet) {
        this.questionSet = questionSet;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerSet)) {
            return false;
        }
        return id != null && id.equals(((AnswerSet) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerSet{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", source='" + getSource() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
