package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A PricingGroup.
 */
@Entity
@Table(name = "T_PRICINGGROUP")
public class PricingGroup implements Serializable {

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

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProductPricingGroup> getProductPricingGroups() {
        return productPricingGroups;
    }

    public void setProductPricingGroups(Set<ProductPricingGroup> productPricingGroups) {
        this.productPricingGroups = productPricingGroups;
    }

    @Override
    public boolean equals(Object o) {
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
