package com.gok.campaign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DataRow.
 */
@Entity
@Table(name = "data_row")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataRow extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "s_no")
    private String sNo;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "source")
    private String source;

    @ManyToOne
    @JsonIgnoreProperties(value = "dataRows", allowSetters = true)
    private File file;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsNo() {
        return sNo;
    }

    public DataRow sNo(String sNo) {
        this.sNo = sNo;
        return this;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getIdentifier() {
        return identifier;
    }

    public DataRow identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSource() {
        return source;
    }

    public DataRow source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public File getFile() {
        return file;
    }

    public DataRow file(File file) {
        this.file = file;
        return this;
    }

    public void setFile(File file) {
        this.file = file;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataRow)) {
            return false;
        }
        return id != null && id.equals(((DataRow) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataRow{" +
            "id=" + getId() +
            ", sNo='" + getsNo() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", source='" + getSource() + "'" +
            "}";
    }
}
