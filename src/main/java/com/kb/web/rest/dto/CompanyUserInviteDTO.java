package com.kb.web.rest.dto;

import com.kb.domain.Company;

/**
 * Created by rdyyak on 24.04.15.
 */
public class CompanyUserInviteDTO {
    private String email;
    private String role;
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

    @Override
    public String toString() {
        return "UserCompanyDTO{" +
            ", company=" + company +
            ", email=" + email +
            ", role=" + role +
            '}';
    }
}
