package com.gok.campaign.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.campaign.domain.Answer} entity.
 */
public class AnswerDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private Long qid;

    private String question;

    private String answer;

    private String metadata;


    private Long answerSetId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Long getAnswerSetId() {
        return answerSetId;
    }

    public void setAnswerSetId(Long answerSetId) {
        this.answerSetId = answerSetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerDTO)) {
            return false;
        }

        return id != null && id.equals(((AnswerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerDTO{" +
            "id=" + getId() +
            ", qid=" + getQid() +
            ", question='" + getQuestion() + "'" +
            ", answer='" + getAnswer() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", answerSetId=" + getAnswerSetId() +
            "}";
    }
}
