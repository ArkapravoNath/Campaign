package com.gok.campaign.service.dto;

import java.io.Serializable;
import com.gok.campaign.domain.enumeration.StatusType;

/**
 * A DTO for the {@link com.gok.campaign.domain.Question} entity.
 */
public class QuestionDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String text;

    private String inputControl;

    private String masterValues;

    private StatusType status;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInputControl() {
        return inputControl;
    }

    public void setInputControl(String inputControl) {
        this.inputControl = inputControl;
    }

    public String getMasterValues() {
        return masterValues;
    }

    public void setMasterValues(String masterValues) {
        this.masterValues = masterValues;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionDTO)) {
            return false;
        }

        return id != null && id.equals(((QuestionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionDTO{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", inputControl='" + getInputControl() + "'" +
            ", masterValues='" + getMasterValues() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
