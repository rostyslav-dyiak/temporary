package com.kb.domain;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A SupplierDetails.
 */
@Entity
@Table(name = "T_SUPPLIERDETAILS")
public class SupplierDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "logo_id")
    private Long logoId;

    @Column(name = "main_picture_id")
    private Long mainPictureId;
	
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

    public Long getLogoId() {
        return logoId;
    }

    public void setLogoId(final Long logoId) {
        this.logoId = logoId;
    }

    public Boolean getPublicPricingVisible() {
        return publicPricingVisible;
    }

    public void setPublicPricingVisible(final Boolean publicPricingVisible) {
        this.publicPricingVisible = publicPricingVisible;
    }

    public Long getMainPictureId() {
        return mainPictureId;
    }

    public void setMainPictureId(final Long mainPictureId) {
        this.mainPictureId = mainPictureId;
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
                ", logoId='" + logoId + "'" +
                ", publicPricingVisible='" + publicPricingVisible + "'" +
                ", mainPictureId='" + mainPictureId + "'" +
                '}';
    }
}
