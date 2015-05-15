package com.kb.domain;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A PricingGroup.
 */
@Entity
@Table(name = "T_PRICINGGROUP")
public class PricingGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pricingGroup")
    private Set<ProductPricingGroup> productPricingGroups = new HashSet<ProductPricingGroup>();

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Set<ProductPricingGroup> getProductPricingGroups() {
        return productPricingGroups;
    }

    public void setProductPricingGroups(final Set<ProductPricingGroup> productPricingGroups) {
        this.productPricingGroups = productPricingGroups;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PricingGroup pricingGroup = (PricingGroup) o;

        if (!Objects.equals(id, pricingGroup.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PricingGroup{" +
            "id=" + id +
            ", groupName='" + groupName + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
