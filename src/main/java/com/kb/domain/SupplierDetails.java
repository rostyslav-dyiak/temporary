package com.kb.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_picture_id")
    private Picture mainPicture;

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

    @OneToMany
    @JoinColumn(name = "supplier_details_id")
    private Set<DayOff> holidays;

    @ManyToMany(cascade = CascadeType.ALL)
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

	public Picture getMainPicture() {
		return mainPicture;
	}

	public void setMainPicture(final Picture mainPicture) {
		this.mainPicture = mainPicture;
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

	public Set<DayOff> getHolidays() {
		return holidays;
	}

	public void setHolidays(final Set<DayOff> holidays) {
		this.holidays = holidays;
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
