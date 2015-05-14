package com.kb.web.rest.dto.company;

import com.kb.domain.BusinessType;
import com.kb.domain.Outlet;
import com.kb.domain.Picture;
import com.kb.domain.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by root on 5/14/15.
 */
public class EateryDto extends CompanyDto{

    private Set<Outlet> outlets = new HashSet<>();

    private Long id;

    private Picture topRightPicture;

    private User contactPerson;

    private String busRegNumber;

    private String billingAddress;

    private String postalCode;

    private String faxNumber;

    private BusinessType businessType;

    public EateryDto(){

    }

    public Set<Outlet> getOutlets() {
        return outlets;
    }

    public void setOutlets(Set<Outlet> outlets) {
        this.outlets = outlets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Picture getTopRightPicture() {
        return topRightPicture;
    }

    public void setTopRightPicture(Picture topRightPicture) {
        this.topRightPicture = topRightPicture;
    }

    public User getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(User contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getBusRegNumber() {
        return busRegNumber;
    }

    public void setBusRegNumber(String busRegNumber) {
        this.busRegNumber = busRegNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EateryDto eateryDto = (EateryDto) o;

        if (outlets != null ? !outlets.equals(eateryDto.outlets) : eateryDto.outlets != null) return false;
        if (id != null ? !id.equals(eateryDto.id) : eateryDto.id != null) return false;
        if (topRightPicture != null ? !topRightPicture.equals(eateryDto.topRightPicture) : eateryDto.topRightPicture != null)
            return false;
        if (contactPerson != null ? !contactPerson.equals(eateryDto.contactPerson) : eateryDto.contactPerson != null)
            return false;
        if (busRegNumber != null ? !busRegNumber.equals(eateryDto.busRegNumber) : eateryDto.busRegNumber != null)
            return false;
        if (billingAddress != null ? !billingAddress.equals(eateryDto.billingAddress) : eateryDto.billingAddress != null)
            return false;
        if (postalCode != null ? !postalCode.equals(eateryDto.postalCode) : eateryDto.postalCode != null) return false;
        if (faxNumber != null ? !faxNumber.equals(eateryDto.faxNumber) : eateryDto.faxNumber != null) return false;
        return !(businessType != null ? !businessType.equals(eateryDto.businessType) : eateryDto.businessType != null);

    }

    @Override
    public int hashCode() {
        int result = outlets != null ? outlets.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (topRightPicture != null ? topRightPicture.hashCode() : 0);
        result = 31 * result + (contactPerson != null ? contactPerson.hashCode() : 0);
        result = 31 * result + (busRegNumber != null ? busRegNumber.hashCode() : 0);
        result = 31 * result + (billingAddress != null ? billingAddress.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (faxNumber != null ? faxNumber.hashCode() : 0);
        result = 31 * result + (businessType != null ? businessType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EateryDto{" +
                "outlets=" + outlets +
                ", id=" + id +
                ", topRightPicture=" + topRightPicture +
                ", contactPerson=" + contactPerson +
                ", busRegNumber='" + busRegNumber + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", faxNumber='" + faxNumber + '\'' +
                ", businessType=" + businessType +
                '}';
    }
}
