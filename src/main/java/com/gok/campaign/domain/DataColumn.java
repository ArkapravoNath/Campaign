package com.gok.campaign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DataColumn.
 */
@Entity
@Table(name = "data_column")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataColumn extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JsonIgnoreProperties(value = "dataColumns", allowSetters = true)
    private DataRow row;

    @ManyToOne
    @JsonIgnoreProperties(value = "dataColumns", allowSetters = true)
    private ColumnMaster column;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public DataColumn value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DataRow getRow() {
        return row;
    }

    public DataColumn row(DataRow dataRow) {
        this.row = dataRow;
        return this;
    }

    public void setRow(DataRow dataRow) {
        this.row = dataRow;
    }

    public ColumnMaster getColumn() {
        return column;
    }

    public DataColumn column(ColumnMaster columnMaster) {
        this.column = columnMaster;
        return this;
    }

    public void setColumn(ColumnMaster columnMaster) {
        this.column = columnMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataColumn)) {
            return false;
        }
        return id != null && id.equals(((DataColumn) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataColumn{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            "}";
    }
}
