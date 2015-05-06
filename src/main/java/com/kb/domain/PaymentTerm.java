package com.kb.domain;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A PaymentTerm.
 */
@Entity
@Table(name = "PAYMENTTERM")
public class PaymentTerm extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "term_type")
    private PaymentType termType;

    @Column(name = "term_name")
    private String termName;

    @Column(name = "description")
    private String description;

    @Column(name = "num_of_days")
    private Integer numOfDays;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(final String termName) {
        this.termName = termName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Integer getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(final Integer numOfDays) {
        this.numOfDays = numOfDays;
    }

    public PaymentType getTermType() {
        return termType;
    }

    public void setTermType(final PaymentType termType) {
        this.termType = termType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaymentTerm paymentTerm = (PaymentTerm) o;

        if (!Objects.equals(id, paymentTerm.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PaymentTerm{" +
            "id=" + id +
            ", termName='" + termName + "'" +
            ", description='" + description + "'" +
            ", numOfDays='" + numOfDays + "'" +
            '}';
    }
}
