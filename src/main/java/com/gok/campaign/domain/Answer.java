package com.gok.campaign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Answer.
 */
@Entity
@Table(name = "answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Answer extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "qid")
    private Long qid;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "metadata")
    private String metadata;

    @ManyToOne
    @JsonIgnoreProperties(value = "answers", allowSetters = true)
    private AnswerSet answerSet;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQid() {
        return qid;
    }

    public Answer qid(Long qid) {
        this.qid = qid;
        return this;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public Answer question(String question) {
        this.question = question;
        return this;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public Answer answer(String answer) {
        this.answer = answer;
        return this;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMetadata() {
        return metadata;
    }

    public Answer metadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public AnswerSet getAnswerSet() {
        return answerSet;
    }

    public Answer answerSet(AnswerSet answerSet) {
        this.answerSet = answerSet;
        return this;
    }

    public void setAnswerSet(AnswerSet answerSet) {
        this.answerSet = answerSet;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Answer)) {
            return false;
        }
        return id != null && id.equals(((Answer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Answer{" +
            "id=" + getId() +
            ", qid=" + getQid() +
            ", question='" + getQuestion() + "'" +
            ", answer='" + getAnswer() + "'" +
            ", metadata='" + getMetadata() + "'" +
            "}";
    }
}
