package com.kb.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A Unit.
 */
@Entity
@Table(name = "UNIT")
public class Unit extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "component_id")
    private Unit component;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUnitSymbol() {
        return unitSymbol;
    }

    public void setUnitSymbol(final String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(final Boolean available) {
        this.available = available;
    }

    public Integer getEquivalentQuantity() {
        return equivalentQuantity;
    }

    public void setEquivalentQuantity(final Integer equivalentQuantity) {
        this.equivalentQuantity = equivalentQuantity;
    }

    public Unit getComponent() {
        return component;
    }

    public void setComponent(final Unit component) {
        this.component = component;
    }
}
