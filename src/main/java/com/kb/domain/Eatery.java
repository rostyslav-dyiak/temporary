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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "bus_registration_number")
    private String bus_registration_number;

    @Column(name = "billing_number")
    private String billing_number;

    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "email")
    private String email;

    @Column(name = "company_contact_number")
    private String company_contact_number;

    @Column(name = "company_fax_number")
    private String company_fax_number;

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

    public String getBus_registration_number() {
        return bus_registration_number;
    }

    public void setBus_registration_number(String bus_registration_number) {
        this.bus_registration_number = bus_registration_number;
    }

    public String getBilling_number() {
        return billing_number;
    }

    public void setBilling_number(String billing_number) {
        this.billing_number = billing_number;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany_contact_number() {
        return company_contact_number;
    }

    public void setCompany_contact_number(String company_contact_number) {
        this.company_contact_number = company_contact_number;
    }

    public String getCompany_fax_number() {
        return company_fax_number;
    }

    public void setCompany_fax_number(String company_fax_number) {
        this.company_fax_number = company_fax_number;
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
                ", bus_registration_number='" + bus_registration_number + "'" +
                ", billing_number='" + billing_number + "'" +
                ", postal_code='" + postal_code + "'" +
                ", email='" + email + "'" +
                ", company_contact_number='" + company_contact_number + "'" +
                ", company_fax_number='" + company_fax_number + "'" +
                '}';
    }
}
