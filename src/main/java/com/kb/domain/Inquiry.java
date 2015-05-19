package com.kb.domain;


import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A Inquiry.
 */
@Entity
@Table(name = "T_INQUIRY")
public class Inquiry extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "delivery_per_week")
    private Integer deliveryPerWeek;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "seen_date")
    private DateTime seenDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Inquiry parent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_term_id")
    private PaymentTerm paymentTerm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eatery_details_id")
    private EateryDetails eateryDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_details_id")
    private SupplierDetails supplierDetails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "inquiry")
    private Set<InquiryOutlet> inquiryOutlets;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "inquiry")
    private Set<InquiryProduct> inquiryProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getDeliveryPerWeek() {
        return deliveryPerWeek;
    }

    public void setDeliveryPerWeek(Integer deliveryPerWeek) {
        this.deliveryPerWeek = deliveryPerWeek;
    }

    public DateTime getSeenDate() {
        return seenDate;
    }

    public void setSeenDate(DateTime seenDate) {
        this.seenDate = seenDate;
    }

    public Inquiry getParent() {
        return parent;
    }

    public void setParent(Inquiry parent) {
        this.parent = parent;
    }

    public PaymentTerm getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(PaymentTerm paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public EateryDetails getEateryDetails() {
        return eateryDetails;
    }

    public void setEateryDetails(EateryDetails eateryDetails) {
        this.eateryDetails = eateryDetails;
    }

    public SupplierDetails getSupplierDetails() {
        return supplierDetails;
    }

    public void setSupplierDetails(SupplierDetails supplierDetails) {
        this.supplierDetails = supplierDetails;
    }

    public Set<InquiryOutlet> getInquiryOutlets() {
        return inquiryOutlets;
    }

    public void setInquiryOutlets(Set<InquiryOutlet> inquiryOutlets) {
        this.inquiryOutlets = inquiryOutlets;
    }

    public Set<InquiryProduct> getInquiryProducts() {
        return inquiryProducts;
    }

    public void setInquiryProducts(Set<InquiryProduct> inquiryProducts) {
        this.inquiryProducts = inquiryProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Inquiry inquiry = (Inquiry) o;

        if ( ! Objects.equals(id, inquiry.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
