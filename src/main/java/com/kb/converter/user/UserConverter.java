package com.kb.converter.user;

import com.kb.converter.AbstractConverter;
import com.kb.domain.User;
import com.kb.web.rest.dto.UserCompanyDTO;
import com.kb.web.rest.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverter<User, UserCompanyDTO> {

	@Override
	public UserCompanyDTO convert(User source, UserCompanyDTO target) {

		UserDTO userDTO = new UserDTO();

		userDTO.setId(source.getId());
		userDTO.setCompany(source.getCompany());
		userDTO.setEmail(source.getEmail());
		userDTO.setFirstName(source.getFirstName());
		userDTO.setLangKey(source.getLangKey());
		userDTO.setLastName(source.getLastName());
		userDTO.setLogin(source.getLogin());
		userDTO.setRole(source.getAuthorities().iterator().next().getName());
		userDTO.setOutletCount(source.getOutlets().size());
		userDTO.setTitle(source.getTitle());
		userDTO.setSalutation(source.getSalutation());
		userDTO.setStatus(source.getStatus());
		userDTO.setContactNumber(source.getContactNumber());
        userDTO.setOutlet(source.getOutletForMember());

		target.setUserDTO(userDTO);
		target.setCompany(source.getCompany());

		return target;
	}

	@Override
	public UserCompanyDTO convert(User source) {

		UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
		UserDTO userDTO = new UserDTO();

		userDTO.setId(source.getId());
		userDTO.setCompany(source.getCompany());
		userDTO.setEmail(source.getEmail());
		userDTO.setFirstName(source.getFirstName());
		userDTO.setLangKey(source.getLangKey());
		userDTO.setLastName(source.getLastName());
		userDTO.setLogin(source.getLogin());
        userDTO.setRole(source.getAuthorities().iterator().next().getName());
        userDTO.setOutletCount(source.getOutlets().size());
		userDTO.setTitle(source.getTitle());
        userDTO.setSalutation(source.getSalutation());
		userDTO.setStatus(source.getStatus());
        userDTO.setContactNumber(source.getContactNumber());
        userDTO.setOutlet(source.getOutletForMember());

		userCompanyDTO.setUserDTO(userDTO);
		userCompanyDTO.setCompany(source.getCompany());

		return userCompanyDTO;
	}

}
