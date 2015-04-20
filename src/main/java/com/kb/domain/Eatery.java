package com.kb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Eatery.
 */
@Entity
@Table(name = "T_EATERY")
public class Eatery implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "busRegistrationNumber")
    private String busRegistrationNumber;

    @Column(name = "billingNumber")
    private String billingNumber;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "email")
    private String email;

    @Column(name = "companyContactNumber")
    private String companyContactNumber;

    @Column(name = "companyFaxNumber")
    private String companyFaxNumber;

    @OneToOne
    private Picture picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getBusRegistrationNumber() {
        return busRegistrationNumber;
    }

    public void setBusRegistrationNumber(String busRegistrationNumber) {
        this.busRegistrationNumber = busRegistrationNumber;
    }

    public String getBillingNumber() {
        return billingNumber;
    }

    public void setBillingNumber(String billingNumber) {
        this.billingNumber = billingNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyContactNumber() {
        return companyContactNumber;
    }

    public void setCompanyContactNumber(String companyContactNumber) {
        this.companyContactNumber = companyContactNumber;
    }

    public String getCompanyFaxNumber() {
        return companyFaxNumber;
    }

    public void setCompanyFaxNumber(String companyFaxNumber) {
        this.companyFaxNumber = companyFaxNumber;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Eatery eatery = (Eatery) o;

        if ( ! Objects.equals(id, eatery.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Eatery{" +
                "id=" + id +
                ", company_name='" + company_name + "'" +
                ", busRegistrationNumber='" + busRegistrationNumber + "'" +
                ", billingNumber='" + billingNumber + "'" +
                ", postalCode='" + postalCode + "'" +
                ", email='" + email + "'" +
                ", companyContactNumber='" + companyContactNumber + "'" +
                ", companyFaxNumber='" + companyFaxNumber + "'" +
                '}';
    }
}
