package com.kb.web.rest.dto;

import com.kb.domain.Company;

/**
 * Created by rdyyak on 24.04.15.
 */
public class UserCompanyDTO {
    private UserDTO userDTO;
    private Company company;

    UserCompanyDTO() {

    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "UserCompanyDTO{" +
            "userDTO=" + userDTO +
            ", company=" + company +
            '}';
    }
}
