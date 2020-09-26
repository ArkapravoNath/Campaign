package com.gok.campaign.service.dto;

import java.io.Serializable;
import com.gok.campaign.domain.enumeration.TaskType;

/**
 * A DTO for the {@link com.gok.campaign.domain.Essentials} entity.
 */
public class EssentialsDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String item;

    private String itemDescription;

    private TaskType taskType;

    private Integer priority;

    private Integer quantity;


    private Long serviceReqId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getServiceReqId() {
        return serviceReqId;
    }

    public void setServiceReqId(Long serviceReqId) {
        this.serviceReqId = serviceReqId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EssentialsDTO)) {
            return false;
        }

        return id != null && id.equals(((EssentialsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EssentialsDTO{" +
            "id=" + getId() +
            ", item='" + getItem() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", taskType='" + getTaskType() + "'" +
            ", priority=" + getPriority() +
            ", quantity=" + getQuantity() +
            ", serviceReqId=" + getServiceReqId() +
            "}";
    }
}
