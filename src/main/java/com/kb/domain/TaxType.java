package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A TaxType.
 */
@Entity
@Table(name = "T_TAXTYPE")
public class TaxType extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "available")
    private Boolean available;

    @Enumerated(EnumType.STRING)
    @Column(name = "calculation_type")
    private CalculationType calculationType;

    @Column(name = "percentage", precision = 10, scale = 2)
    private BigDecimal percentage;

    @Column(name = "is_default")
    private Boolean isDefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public CalculationType getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(CalculationType calculationType) {
        this.calculationType = calculationType;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaxType taxType = (TaxType) o;

        if (!Objects.equals(id, taxType.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TaxType{" +
            "id=" + id +
            ", code='" + code + "'" +
            ", name='" + name + "'" +
            ", available='" + available + "'" +
            ", calculationType='" + calculationType + "'" +
            ", percentage='" + percentage + "'" +
            ", default='" + isDefault + "'" +
            '}';
    }
}
