package com.kb.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A EateryDetails.
 */
@Entity
@Table(name = "T_EATERYDETAILS")
public class EateryDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name = "eatery_id")
	private Long eateryId;
	
	@ManyToOne
	@JoinColumn(name = "eatery_id", insertable = false, updatable = false)
	@JsonIgnore
	private Company eatery;
	
    @Column(name = "code")
    private String code;

    @Column(name = "bus_reg_number")
    private String busRegNumber;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "logo_id")
    private Long logoId;

    @Column(name = "top_right_picture_id")
    private Long topRightPictureId;

    @Column(name = "contact_person_id")
    private Long contactPersonId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getEateryId() {
		return eateryId;
	}

	public void setEateryId(final Long eateryId) {
		this.eateryId = eateryId;
	}

	public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getBusRegNumber() {
        return busRegNumber;
    }

    public void setBusRegNumber(final String BusRegNumber) {
        this.busRegNumber = BusRegNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(final String BillingAddress) {
        this.billingAddress = BillingAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(final String PostalCode) {
        this.postalCode = PostalCode;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(final String FaxNumber) {
        this.faxNumber = FaxNumber;
    }

    public Long getLogoId() {
        return logoId;
    }

    public void setLogoId(final Long logoId) {
        this.logoId = logoId;
    }

    public Long getTopRightPictureId() {
        return topRightPictureId;
    }

    public void setTopRightPictureId(final Long topRightPictureId) {
        this.topRightPictureId = topRightPictureId;
    }

    public Long getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(final Long contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

}
