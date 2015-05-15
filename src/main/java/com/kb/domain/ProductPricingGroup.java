package com.kb.domain;


import javax.persistence.*;
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
    private PricingGroup pricingGroup;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductPricingGroup{" +
            "id=" + id +
            ", price=" + price +
            ", pricingGroup=" + pricingGroup +
            ", product=" + product +
            '}';
    }
}
