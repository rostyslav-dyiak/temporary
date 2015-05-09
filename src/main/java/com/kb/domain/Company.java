package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * A Company.
 */
@Entity
@Table(name = "T_COMPANY")
public class Company extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@OneToOne(cascade = CascadeType.ALL,mappedBy = "eatery")
	private EateryDetails eateryDetails;

	@OneToOne(cascade = CascadeType.ALL,mappedBy = "supplier")
	private SupplierDetails supplierDetails;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

	@Column(name = "company_type")
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

    @Column(name = "code")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<User> users = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<Outlet> outlets = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<Contact> contacts = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "logo_id")
    private Picture logo;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(final String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(final CompanyType companyType) {
        this.companyType = companyType;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(final CompanyStatus status) {
        this.status = status;
    }

    public EateryDetails getEateryDetails() {
		return eateryDetails;
	}

	public void setEateryDetails(final EateryDetails eateryDetails) {
		this.eateryDetails = eateryDetails;
	}

	public SupplierDetails getSupplierDetails() {
		return supplierDetails;
	}

	public void setSupplierDetails(final SupplierDetails supplierDetails) {
		this.supplierDetails = supplierDetails;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(final Set<User> users) {
		this.users = users;
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Picture getLogo() {
        return logo;
    }

    public void setLogo(Picture logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Company company = (Company) o;

        if ( ! Objects.equals(id, company.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", email='" + email + "'" +
                ", contactNumber='" + contactNumber + "'" +
                ", companyType='" + companyType + "'" +
                ", status='" + status + "'" +
                '}';
    }
}
