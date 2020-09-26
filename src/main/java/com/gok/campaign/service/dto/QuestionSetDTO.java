package com.gok.campaign.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.gok.campaign.domain.QuestionSet} entity.
 */
public class QuestionSetDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String name;

    private String metadata;

    private Set<QuestionDTO> questions = new HashSet<>();
    
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

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Set<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionDTO> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSetDTO)) {
            return false;
        }

        return id != null && id.equals(((QuestionSetDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSetDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", questions='" + getQuestions() + "'" +
            "}";
    }
}
