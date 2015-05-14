package com.kb.web.rest.dto.company;

import com.kb.domain.CompanyStatus;
import com.kb.domain.CompanyType;
import com.kb.domain.Picture;
import com.kb.domain.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by root on 5/14/15.
 */
public abstract class CompanyDto {

    protected String name;

    protected String email;

    protected String contactNumber;

    protected CompanyType companyType;

    protected CompanyStatus status;

    protected String code;

    private Set<User> users = new HashSet<>();

    private Picture logo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Picture getLogo() {
        return logo;
    }

    public void setLogo(Picture logo) {
        this.logo = logo;
    }
}
