package com.kb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A Outlet.
 */
@Entity
@Table(name = "T_OUTLET")
public class Outlet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "deliveryAddress")
    private String deliveryAddress;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "eatery_member_outlet", joinColumns = {@JoinColumn(name = " eatery_member_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "outlet_id", referencedColumnName = "id")})
    private Set<EateryMember> eateryMembers;

    @ManyToOne
    private Eatery eatery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String delivery_address) {
        this.deliveryAddress = delivery_address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contact_number) {
        this.contactNumber = contact_number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postal_code) {
        this.postalCode = postal_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Eatery getEatery() {
        return eatery;
    }

    public void setEatery(Eatery eatery) {
        this.eatery = eatery;
    }

    public Set<EateryMember> getEateryMembers() {
        return eateryMembers;
    }

    public void setEateryMembers(Set<EateryMember> eateryMembers) {
        this.eateryMembers = eateryMembers;
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

        if (!Objects.equals(id, outlet.id)) return false;

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
            ", title='" + title + "'" +
            ", deliveryAddress='" + deliveryAddress + "'" +
            ", contactNumber='" + contactNumber + "'" +
            ", postalCode='" + postalCode + "'" +
            ", email='" + email + "'" +
            '}';
    }


}
