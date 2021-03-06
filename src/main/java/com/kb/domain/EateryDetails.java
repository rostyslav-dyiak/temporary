package com.kb.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EateryDetails.
 */
@Entity
@Table(name = "T_EATERYDETAILS")
public class EateryDetails extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
	@OneToOne
	@JoinColumn(name = "eatery_id")
	private Company eatery;

    @ManyToOne
    @JoinColumn(name = "top_right_picture_id")
    private Picture topRightPicture;

    @ManyToOne
    @JoinColumn(name = "contact_person_id")
    private User contactPerson;

    @Column(name = "bus_reg_number")
    private String busRegNumber;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "fax_number")
    private String faxNumber;

    @OneToOne
    @JoinColumn(name= "business_type_id")
    private BusinessType businessType;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

	public Company getEatery() {
		return eatery;
	}

	public void setEatery(final Company eatery) {
		this.eatery = eatery;
	}

	public Picture getTopRightPicture() {
		return topRightPicture;
	}

	public void setTopRightPicture(final Picture topRightPicture) {
		this.topRightPicture = topRightPicture;
	}

	public User getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(final User contactPerson) {
		this.contactPerson = contactPerson;
	}

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EateryDetails outlet = (EateryDetails) o;

        if ( ! Objects.equals(id, outlet.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

	@Override
	public String toString() {
		return "EateryDetails [id=" + id  + ", busRegNumber="
				+ busRegNumber + ", billingAddress=" + billingAddress
				+ ", postalCode=" + postalCode + ", faxNumber=" + faxNumber
				+ "]";
	}

}
