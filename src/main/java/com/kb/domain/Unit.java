package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Unit.
 */
@Entity
@Table(name = "UNIT")
public class Unit extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "unit_symbol", nullable = false)
    private String unitSymbol;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "equivalent_quantity")
    private Integer equivalentQuantity;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private Unit component;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitSymbol() {
        return unitSymbol;
    }

    public void setUnitSymbol(String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getEquivalentQuantity() {
        return equivalentQuantity;
    }

    public void setEquivalentQuantity(Integer equivalentQuantity) {
        this.equivalentQuantity = equivalentQuantity;
    }

    public Unit getComponent() {
        return component;
    }

    public void setComponent(Unit component) {
        this.component = component;
    }
}
