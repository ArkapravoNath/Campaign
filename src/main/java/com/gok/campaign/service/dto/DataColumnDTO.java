package com.gok.campaign.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.campaign.domain.DataColumn} entity.
 */
public class DataColumnDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String value;


    private Long rowId;

    private Long columnId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long dataRowId) {
        this.rowId = dataRowId;
    }

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnMasterId) {
        this.columnId = columnMasterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataColumnDTO)) {
            return false;
        }

        return id != null && id.equals(((DataColumnDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataColumnDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", rowId=" + getRowId() +
            ", columnId=" + getColumnId() +
            "}";
    }
}
