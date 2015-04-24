package com.kb.domain;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

	@OneToOne
	@JoinColumn(name = "eatery_id")
	private Company eatery;
	
    @ManyToOne
    @JoinColumn(name = "logo_id")
    private Picture logo;
    
    @ManyToOne
    @JoinColumn(name = "top_right_picture_id")
    private Picture topRightPicture;
    
    @ManyToOne
    @JoinColumn(name = "contact_person_id")
    private User contactPerson;	
	
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

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

	public Company getEatery() {
		return eatery;
	}

	public void setEatery(final Company eatery) {
		this.eatery = eatery;
	}

	public Picture getLogo() {
		return logo;
	}

	public void setLogo(final Picture logo) {
		this.logo = logo;
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
		return "EateryDetails [id=" + id + ", code=" + code + ", busRegNumber="
				+ busRegNumber + ", billingAddress=" + billingAddress
				+ ", postalCode=" + postalCode + ", faxNumber=" + faxNumber
				+ "]";
	}
    
}
