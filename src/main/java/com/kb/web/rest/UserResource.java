package com.kb.web.rest;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.User;
import com.kb.repository.UserRepository;
import com.kb.security.SecurityUtils;
import com.kb.service.company.CompanyService;
import com.kb.web.rest.dto.UserDTO;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private CompanyService companyService;

    /**
     * GET  /users -> get all users.
     */
    @RequestMapping(value = "/users",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<User> getAll() {
        log.debug("REST request to get all Users");
        return userRepository.findAll();
    }

    /**
     * GET  /users/:login -> get the "login" user.
     */
    @RequestMapping(value = "/users/{login}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    ResponseEntity<User> getUser(@PathVariable final String login) {
        log.debug("REST request to get User : {}", login);
        return userRepository.findOneByLogin(login)
            .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

	@RequestMapping(value = "/users",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> update(@RequestBody final UserDTO userDTO) {
        log.debug("REST request to update User");
        Optional<User> optionalUser = userRepository.findOneByEmail(SecurityUtils.getCurrentLogin());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setSalutation(userDTO.getSalutation());
            user.setFirstName(userDTO.getFirstName());
            user.setContactNumber(userDTO.getContactNumber());
            user.setTitle(userDTO.getTitle());
            user.getCompany().setLogo(userDTO.getCompany().getLogo());
            companyService.save(user.getCompany());
            return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("User not found", HttpStatus.BAD_REQUEST);
        }
    }
}
