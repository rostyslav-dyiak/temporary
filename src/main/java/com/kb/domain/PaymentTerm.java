package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PaymentTerm.
 */
@Entity
@Table(name = "PAYMENTTERM")
public class PaymentTerm extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "term_name")
    private String termName;

    @Column(name = "description")
    private String description;

    @Column(name = "num_of_days")
    private Integer numOfDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(Integer numOfDays) {
        this.numOfDays = numOfDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaymentTerm paymentTerm = (PaymentTerm) o;

        if ( ! Objects.equals(id, paymentTerm.id)) return false;

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
