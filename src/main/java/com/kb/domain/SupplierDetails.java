package com.kb.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * A SupplierDetails.
 */
@Entity
@Table(name = "T_SUPPLIERDETAILS")
public class SupplierDetails extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
	@OneToOne
	@JoinColumn(name = "supplier_id")
	private Company supplier;

    @ManyToOne
    @JoinColumn(name = "logo_id")
    private Picture logo;

    @ManyToOne
    @JoinColumn(name = "main_picture_id")
    private Picture mainPicture;

    @Column(name = "code")
    private String code;

    @Column(name = "bus_reg_number")
    private String busRegNumber;

    @Column(name = "bus_description")
    private String busDescription;

    @Column(name = "address")
    private String address;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "gst_registered")
    private Boolean gstRegistered;

    @Column(name = "gst_registration_number")
    private String gstRegistrationNumber;

    @Column(name = "public_pricing_visible")
    private Boolean publicPricingVisible;

    @ManyToMany
    @JoinTable(name = "t_supplier_details_picture",
    	joinColumns = @JoinColumn(name = "supplier_details_picture_id", referencedColumnName = "id"),
    	inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id"))
    private Set<Picture> pictures;

    public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Company getSupplier() {
		return supplier;
	}

	public void setSupplier(final Company supplier) {
		this.supplier = supplier;
	}

	public Picture getLogo() {
		return logo;
	}

	public void setLogo(final Picture logo) {
		this.logo = logo;
	}

	public Picture getMainPicture() {
		return mainPicture;
	}

	public void setMainPicture(final Picture mainPicture) {
		this.mainPicture = mainPicture;
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

	public void setBusRegNumber(final String busRegNumber) {
		this.busRegNumber = busRegNumber;
	}

	public String getBusDescription() {
		return busDescription;
	}

	public void setBusDescription(final String busDescription) {
		this.busDescription = busDescription;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(final String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public Boolean getGstRegistered() {
		return gstRegistered;
	}

	public void setGstRegistered(final Boolean gstRegistered) {
		this.gstRegistered = gstRegistered;
	}

	public String getGstRegistrationNumber() {
		return gstRegistrationNumber;
	}

	public void setGstRegistrationNumber(final String gstRegistrationNumber) {
		this.gstRegistrationNumber = gstRegistrationNumber;
	}

	public Boolean getPublicPricingVisible() {
		return publicPricingVisible;
	}

	public void setPublicPricingVisible(final Boolean publicPricingVisible) {
		this.publicPricingVisible = publicPricingVisible;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(final Set<Picture> pictures) {
		this.pictures = pictures;
	}

	@Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SupplierDetails supplierDetails = (SupplierDetails) o;

        if ( ! Objects.equals(id, supplierDetails.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SupplierDetails{" +
                "id=" + id +
                ", code='" + code + "'" +
                ", busRegNumber='" + busRegNumber + "'" +
                ", busDescription='" + busDescription + "'" +
                ", address='" + address + "'" +
                ", faxNumber='" + faxNumber + "'" +
                ", gstRegistered='" + gstRegistered + "'" +
                ", gstRegistrationNumber='" + gstRegistrationNumber + "'" +
                ", publicPricingVisible='" + publicPricingVisible + "'" +
                '}';
    }
}
