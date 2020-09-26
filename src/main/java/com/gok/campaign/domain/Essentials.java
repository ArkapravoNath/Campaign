package com.gok.campaign.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import com.gok.campaign.domain.enumeration.TaskType;

/**
 * A Essentials.
 */
@Entity
@Table(name = "essentials")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Essentials extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "item_description")
    private String itemDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_type")
    private TaskType taskType;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne
    @JoinColumn(unique = true)
    private ServiceReq serviceReq;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public Essentials item(String item) {
        this.item = item;
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Essentials itemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public Essentials taskType(TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Integer getPriority() {
        return priority;
    }

    public Essentials priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Essentials quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ServiceReq getServiceReq() {
        return serviceReq;
    }

    public Essentials serviceReq(ServiceReq serviceReq) {
        this.serviceReq = serviceReq;
        return this;
    }

    public void setServiceReq(ServiceReq serviceReq) {
        this.serviceReq = serviceReq;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Essentials)) {
            return false;
        }
        return id != null && id.equals(((Essentials) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Essentials{" +
            "id=" + getId() +
            ", item='" + getItem() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", taskType='" + getTaskType() + "'" +
            ", priority=" + getPriority() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
