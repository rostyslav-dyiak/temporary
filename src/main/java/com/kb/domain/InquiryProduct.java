package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A InquiryProduct.
 */
@Entity
@Table(name = "T_INQUIRY_PRODUCT")
public class InquiryProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quanity_per_week")
    private Integer quanityPerWeek;

    @ManyToOne
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuanityPerWeek() {
        return quanityPerWeek;
    }

    public void setQuanityPerWeek(Integer quanityPerWeek) {
        this.quanityPerWeek = quanityPerWeek;
    }

    public Inquiry getInquiry() {
        return inquiry;
    }

    public void setInquiry(Inquiry inquiry) {
        this.inquiry = inquiry;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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

        InquiryProduct inquiryProduct = (InquiryProduct) o;

        if ( ! Objects.equals(id, inquiryProduct.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
