package com.kb.web.rest.dto;

import com.kb.domain.Company;
import com.kb.domain.Outlet;
import com.kb.domain.Salutation;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotNull
    private Long id;

    @Size(min = 1, max = 50)
    private String login;

    @Size(min = 5, max = 100)
    private String password;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private Salutation salutation;

    @Size(min = 2, max = 5)
    private String langKey;

    private Integer outletCount;

    private String role;

    private String title;

    private Company company;

    private String contactNumber;

    private String status;

    private Outlet outlet;


    public UserDTO() {
    }

    public UserDTO(String login, String password, String firstName, String lastName, String email, String langKey, Company company,
                   String role, String title, Salutation salutation, String contactNumber) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.langKey = langKey;
        this.role = role;
        this.company = company;
        this.title = title;
        this.salutation = salutation;
        this.contactNumber = contactNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLangKey() {
        return langKey;
    }

    public String getRole() {
        return role;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setLangKey(final String langKey) {
        this.langKey = langKey;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getOutletCount() {
        return outletCount;
    }

    public void setOutletCount(Integer outletCount) {
        this.outletCount = outletCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Salutation getSalutation() {
        return salutation;
    }

    public void setSalutation(Salutation salutation) {
        this.salutation = salutation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Outlet getOutlet() {
        return outlet;
    }

    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", langKey='" + langKey + '\'' +
            ", roles=" + role +
            '}';
    }
}
