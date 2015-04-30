package com.kb.web.rest.dto;

import com.kb.domain.Company;
import com.kb.domain.Salutation;

public class SupplierInviteDTO {

	private Salutation salutation;
	private String firstName;
	private String title;
	private String status;
	private String email;
    private String role;
    private String contactNumber;
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Salutation getSalutation() {
		return salutation;
	}

	public void setSalutation(Salutation salutation) {
		this.salutation = salutation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
    public String toString() {
        return "UserCompanyDTO{" +
            ", company=" + company +
            ", email=" + email +
            ", role=" + role +
            '}';
    }
	
}
