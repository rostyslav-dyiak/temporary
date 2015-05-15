package com.kb.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A ProductPricingGroup.
 */
@Entity
@Table(name = "T_PRODUCTPRICINGGROUP")
public class ProductPricingGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "price", precision=10, scale=2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "pricing_group_id")
    PricingGroup pricingGroup;

    @ManyToOne
    @JoinColumn(name = "product_id")
    PricingGroup product;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public PricingGroup getPricingGroup() {
        return pricingGroup;
    }

    public void setPricingGroup(final PricingGroup pricingGroup) {
        this.pricingGroup = pricingGroup;
    }

    public PricingGroup getProduct() {
        return product;
    }

    public void setProduct(final PricingGroup product) {
        this.product = product;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductPricingGroup productPricingGroup = (ProductPricingGroup) o;

        if ( ! Objects.equals(id, productPricingGroup.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProductPricingGroup{" +
                "id=" + id +
                ", price='" + price + "'" +
                '}';
    }
}
