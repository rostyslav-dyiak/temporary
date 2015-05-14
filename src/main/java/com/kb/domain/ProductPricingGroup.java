package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A ProductPricingGroup.
 */
@Entity
@Table(name = "T_PRODUCTPRICINGGROUP")
public class ProductPricingGroup implements Serializable {

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

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PricingGroup getPricingGroup() {
        return pricingGroup;
    }

    public void setPricingGroup(PricingGroup pricingGroup) {
        this.pricingGroup = pricingGroup;
    }

    public PricingGroup getProduct() {
        return product;
    }

    public void setProduct(PricingGroup product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
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
