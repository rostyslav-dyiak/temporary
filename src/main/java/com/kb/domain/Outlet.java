package com.kb.domain;


import com.kb.domain.status.OutletStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Outlet.
 */
@Entity
@Table(name = "T_OUTLET")
public class Outlet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OutletStatus status;

    @ManyToOne
    @JoinColumn(name = "outlet_id")
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(OutletStatus status) {
        this.status = status;
    }

    public OutletStatus getStatus() {
        return status;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Outlet outlet = (Outlet) o;

        if ( ! Objects.equals(id, outlet.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Outlet{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", deliveryAddress='" + deliveryAddress + "'" +
                ", postalCode='" + postalCode + "'" +
                ", contactNumber='" + contactNumber + "'" +
                ", email='" + email + "'" +
                '}';
    }
}
