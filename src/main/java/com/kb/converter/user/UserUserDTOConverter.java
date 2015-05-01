package com.kb.converter.user;

import com.kb.converter.AbstractConverter;
import com.kb.domain.Authority;
import com.kb.domain.User;
import com.kb.web.rest.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Created by rdyyak on 01.05.15.
 */
@Component
public class UserUserDTOConverter extends AbstractConverter<User, UserDTO> {
    @Override
    public UserDTO convert(User user, UserDTO dto) {

        dto.setLogin(user.getLogin());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setLangKey(user.getLangKey());
        dto.setCompany(user.getCompany());
        dto.setRole(user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toCollection(LinkedList::new)).get(0));
        dto.setTitle(user.getTitle());
        dto.setSalutation(user.getSalutation());
        dto.setContactNumber(user.getContactNumber());

        return dto;
    }

    @Override
    public UserDTO convert(User user) {
        return new UserDTO(
            user.getLogin(),
            null,
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getLangKey(),
            user.getCompany(),
            user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toCollection(LinkedList::new)).get(0),
            user.getTitle(),
            user.getSalutation(),
            user.getContactNumber()
        );
    }
}
