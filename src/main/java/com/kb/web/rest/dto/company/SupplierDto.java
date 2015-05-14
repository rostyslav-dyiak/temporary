package com.kb.web.rest.dto.company;

import com.kb.domain.Contact;
import com.kb.domain.DayOff;
import com.kb.domain.Picture;
import com.kb.domain.Product;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by root on 5/14/15.
 */
public class SupplierDto extends CompanyDto {

    private Long id;

    private Picture mainPicture;

    private String busRegNumber;

    private String busDescription;

    private String address;

    private String faxNumber;

    private Boolean gstRegistered;

    private String gstRegistrationNumber;

    private Boolean publicPricingVisible;

    private Set<DayOff> holidays;

    private Set<Picture> pictures;

    private Set<Contact> contacts = new HashSet<>();

    private Set<Product> products = new HashSet<>();

    public SupplierDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Picture getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(Picture mainPicture) {
        this.mainPicture = mainPicture;
    }

    public String getBusRegNumber() {
        return busRegNumber;
    }

    public void setBusRegNumber(String busRegNumber) {
        this.busRegNumber = busRegNumber;
    }

    public String getBusDescription() {
        return busDescription;
    }

    public void setBusDescription(String busDescription) {
        this.busDescription = busDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public Boolean getGstRegistered() {
        return gstRegistered;
    }

    public void setGstRegistered(Boolean gstRegistered) {
        this.gstRegistered = gstRegistered;
    }

    public String getGstRegistrationNumber() {
        return gstRegistrationNumber;
    }

    public void setGstRegistrationNumber(String gstRegistrationNumber) {
        this.gstRegistrationNumber = gstRegistrationNumber;
    }

    public Boolean getPublicPricingVisible() {
        return publicPricingVisible;
    }

    public void setPublicPricingVisible(Boolean publicPricingVisible) {
        this.publicPricingVisible = publicPricingVisible;
    }

    public Set<DayOff> getHolidays() {
        return holidays;
    }

    public void setHolidays(Set<DayOff> holidays) {
        this.holidays = holidays;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupplierDto that = (SupplierDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (mainPicture != null ? !mainPicture.equals(that.mainPicture) : that.mainPicture != null) return false;
        if (busRegNumber != null ? !busRegNumber.equals(that.busRegNumber) : that.busRegNumber != null) return false;
        if (busDescription != null ? !busDescription.equals(that.busDescription) : that.busDescription != null)
            return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (faxNumber != null ? !faxNumber.equals(that.faxNumber) : that.faxNumber != null) return false;
        if (gstRegistered != null ? !gstRegistered.equals(that.gstRegistered) : that.gstRegistered != null)
            return false;
        if (gstRegistrationNumber != null ? !gstRegistrationNumber.equals(that.gstRegistrationNumber) : that.gstRegistrationNumber != null)
            return false;
        if (publicPricingVisible != null ? !publicPricingVisible.equals(that.publicPricingVisible) : that.publicPricingVisible != null)
            return false;
        if (holidays != null ? !holidays.equals(that.holidays) : that.holidays != null) return false;
        if (pictures != null ? !pictures.equals(that.pictures) : that.pictures != null) return false;
        if (contacts != null ? !contacts.equals(that.contacts) : that.contacts != null) return false;
        return !(products != null ? !products.equals(that.products) : that.products != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mainPicture != null ? mainPicture.hashCode() : 0);
        result = 31 * result + (busRegNumber != null ? busRegNumber.hashCode() : 0);
        result = 31 * result + (busDescription != null ? busDescription.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (faxNumber != null ? faxNumber.hashCode() : 0);
        result = 31 * result + (gstRegistered != null ? gstRegistered.hashCode() : 0);
        result = 31 * result + (gstRegistrationNumber != null ? gstRegistrationNumber.hashCode() : 0);
        result = 31 * result + (publicPricingVisible != null ? publicPricingVisible.hashCode() : 0);
        result = 31 * result + (holidays != null ? holidays.hashCode() : 0);
        result = 31 * result + (pictures != null ? pictures.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SupplierDto{" +
                "id=" + id +
                ", mainPicture=" + mainPicture +
                ", busRegNumber='" + busRegNumber + '\'' +
                ", busDescription='" + busDescription + '\'' +
                ", address='" + address + '\'' +
                ", faxNumber='" + faxNumber + '\'' +
                ", gstRegistered=" + gstRegistered +
                ", gstRegistrationNumber='" + gstRegistrationNumber + '\'' +
                ", publicPricingVisible=" + publicPricingVisible +
                ", holidays=" + holidays +
                ", pictures=" + pictures +
                ", contacts=" + contacts +
                ", products=" + products +
                '}';
    }
}
