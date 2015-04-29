package com.kb.domain;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kb.domain.status.OutletStatus;

/**
 * A Outlet.
 */
@Entity
@Table(name = "T_OUTLET")
public class Outlet extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

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
    @JoinColumn(name = "company_id")
    private Company company;
    
    @ManyToMany(mappedBy="outlets")
    Set<User> suppliers = new HashSet<>();

    public Outlet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(final String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(final String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setStatus(final OutletStatus status) {
        this.status = status;
    }

    public OutletStatus getStatus() {
        return status;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public Set<User> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<User> suppliers) {
		this.suppliers = suppliers;
	}

	@Override
    public boolean equals(final Object o) {
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
            ", name='" + name + "'" +
            ", deliveryAddress='" + deliveryAddress + "'" +
            ", postalCode='" + postalCode + "'" +
            ", contactNumber='" + contactNumber + "'" +
            ", email='" + email + "'" +
            '}';
    }
}
