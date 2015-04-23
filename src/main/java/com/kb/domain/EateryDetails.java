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
	
    @Column(name = "logo_id")
    private Long logoId;
	
    @ManyToOne
    @JoinColumn(name = "logo_id", insertable = false, updatable = false)
    @JsonIgnore
    private Picture logo;
    
    @Column(name = "top_right_picture_id")
    private Long topRightPictureId;

    @ManyToOne
    @JoinColumn(name = "top_right_picture_id", insertable = false, updatable = false)
    @JsonIgnore
    private Picture topRightPicture;
    
    @Column(name = "contact_person_id")
    private Long contactPersonId;

    @ManyToOne
    @JoinColumn(name = "contact_person_id", insertable = false, updatable = false)
    @JsonIgnore
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((billingAddress == null) ? 0 : billingAddress.hashCode());
		result = prime * result
				+ ((busRegNumber == null) ? 0 : busRegNumber.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((contactPersonId == null) ? 0 : contactPersonId.hashCode());
		result = prime * result
				+ ((eateryId == null) ? 0 : eateryId.hashCode());
		result = prime * result
				+ ((faxNumber == null) ? 0 : faxNumber.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logoId == null) ? 0 : logoId.hashCode());
		result = prime * result
				+ ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime
				* result
				+ ((topRightPictureId == null) ? 0 : topRightPictureId
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		EateryDetails other = (EateryDetails) obj;
		if (billingAddress == null) {
			if (other.billingAddress != null) {
				return false;
			}
		} else if (!billingAddress.equals(other.billingAddress)) {
			return false;
		}
		if (busRegNumber == null) {
			if (other.busRegNumber != null) {
				return false;
			}
		} else if (!busRegNumber.equals(other.busRegNumber)) {
			return false;
		}
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (contactPersonId == null) {
			if (other.contactPersonId != null) {
				return false;
			}
		} else if (!contactPersonId.equals(other.contactPersonId)) {
			return false;
		}
		if (eateryId == null) {
			if (other.eateryId != null) {
				return false;
			}
		} else if (!eateryId.equals(other.eateryId)) {
			return false;
		}
		if (faxNumber == null) {
			if (other.faxNumber != null) {
				return false;
			}
		} else if (!faxNumber.equals(other.faxNumber)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (logoId == null) {
			if (other.logoId != null) {
				return false;
			}
		} else if (!logoId.equals(other.logoId)) {
			return false;
		}
		if (postalCode == null) {
			if (other.postalCode != null) {
				return false;
			}
		} else if (!postalCode.equals(other.postalCode)) {
			return false;
		}
		if (topRightPictureId == null) {
			if (other.topRightPictureId != null) {
				return false;
			}
		} else if (!topRightPictureId.equals(other.topRightPictureId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "EateryDetails [id=" + id + ", eateryId=" + eateryId
				+ ", logoId=" + logoId + ", topRightPictureId="
				+ topRightPictureId + ", contactPersonId=" + contactPersonId
				+ ", code=" + code + ", busRegNumber=" + busRegNumber
				+ ", billingAddress=" + billingAddress + ", postalCode="
				+ postalCode + ", faxNumber=" + faxNumber + "]";
	}

}
