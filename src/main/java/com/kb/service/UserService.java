package com.kb.service;

import com.kb.domain.*;
import com.kb.repository.AuthorityRepository;
import com.kb.repository.CompanyRepository;
import com.kb.repository.PersistentTokenRepository;
import com.kb.repository.UserRepository;
import com.kb.security.SecurityUtils;
import com.kb.service.util.RandomUtil;
import com.kb.web.rest.dto.UserDTO;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private final String ACTIVE_STATUS = "ACTIVE";
    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PersistentTokenRepository persistentTokenRepository;

    @Inject
    private AuthorityRepository authorityRepository;

    @Inject
    private CompanyRepository companyRepository;

    public User getUserByRegistrationKey(String key) {
        log.debug("Getting user for activation key {}", key);
        Optional<User> userOptional = userRepository.findOneByActivationKey(key);
        User user = userOptional.get();
        return user;
    }

    public User createUserInformation(String email, String password,
                                      String firstName, String lastName, String langKey, Company company,
                                      String role) {
        User newUser = new User();
        Authority authority = authorityRepository.findOne(role);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(email);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setLangKey(langKey);
        newUser.setCompany(company);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User createUserInformation(Salutation salutation, String firstName,
                                      String title, String status, String email, String role,
                                      String contactNumber, Company company) {
        User newUser = new User();
        Authority authority = authorityRepository.findOne(role);
        Set<Authority> authorities = new HashSet<>();
        newUser.setLogin(email);
        newUser.setSalutation(salutation);
        newUser.setTitle(title);
        newUser.setStatus(status);
        newUser.setContactNumber(contactNumber);
        // new user gets initially a generated password
        newUser.setFirstName(firstName);
        newUser.setEmail(email);
        newUser.setCompany(company);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User createUserInformation(Salutation salutation, String firstName,
                                      String title, String status, String email, String role,
                                      String contactNumber, Company company, Outlet outlet) {
        User newUser = new User();
        Authority authority = authorityRepository.findOne(role);
        Set<Authority> authorities = new HashSet<>();
        newUser.setLogin(email);
        newUser.setSalutation(salutation);
        newUser.setTitle(title);
        newUser.setStatus(status);
        newUser.setContactNumber(contactNumber);
        // new user gets initially a generated password
        newUser.setFirstName(firstName);
        newUser.setEmail(email);
        newUser.setCompany(company);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        newUser.setOutletForMember(outlet);
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public UserDTO updateUserInformation(UserDTO userDTO, Long companyId) {
        Company company = companyRepository.getOne(companyId);
        company.setContactNumber(userDTO.getContactNumber());
        company.setStatus(CompanyStatus.ACTIVE);
        userRepository.findOneByEmail(userDTO.getEmail()).ifPresent(u -> {
            String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
            u.setFirstName(userDTO.getFirstName());
            u.setContactNumber(userDTO.getContactNumber());
            u.setPassword(encryptedPassword);
            u.setActivated(true);
            u.setActivationKey(null);
            u.setStatus(ACTIVE_STATUS);
            userRepository.save(u);
            log.debug("Changed Information for User: {}", u);

        });
        userDTO.setPassword(null);
        return userDTO;
    }

    public void updateUserInformation(String firstName, String lastName,
                                      String email) {
        userRepository.findOneByEmail(SecurityUtils.getCurrentLogin())
            .ifPresent(u -> {
                u.setFirstName(firstName);
                u.setLastName(lastName);
                u.setEmail(email);
                userRepository.save(u);
                log.debug("Changed Information for User: {}", u);
            });
    }

    public void changePassword(String password) {
        userRepository.findOneByEmail(SecurityUtils.getCurrentLogin())
            .ifPresent(
                u -> {
                    String encryptedPassword = passwordEncoder
                        .encode(password);
                    u.setPassword(encryptedPassword);
                    userRepository.save(u);
                    log.debug("Changed password for User: {}", u);
                });
    }

    public boolean checkUserPassword(String password) {
        Optional<User> optionalUser = userRepository
            .findOneByEmail(SecurityUtils.getCurrentLogin());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.debug("Check user password for User: {}", user);
            return passwordEncoder.matches(password, user.getPassword());
        } else {
            log.debug("Password doesn't match for User: ");
            return false;
        }
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        User currentUser = userRepository.findOneByEmail(
            SecurityUtils.getCurrentLogin()).get();
        currentUser.getAuthorities().size(); // eagerly load the association
        return currentUser;
    }

    public User createInitialUserInformation(String email, Company company,
                                             String role) {
        User newUser = new User();
        Authority authority = authorityRepository.findOne(role);
        Set<Authority> authorities = new HashSet<>();
        newUser.setLogin(email);
        // new user gets initially a generated password
        newUser.setEmail(email);
        newUser.setCompany(company);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }


    public User changeUserEmail(String oldEmail, String newEmail) {
        Optional<User> userOptional = userRepository.findOneByEmail(oldEmail);
        User user = userOptional.get();
        user.setEmail(newEmail);
        user.setActivated(false);
        user.setActivationKey(RandomUtil.generateActivationKey());
        userRepository.save(user);
        log.debug("Updated activation key for User: {}", user);
        return user;
    }


    /**
     * Persistent Token are used for providing automatic authentication, they
     * should be automatically deleted after 30 days.
     * <p/>
     * <p>
     * This is scheduled to get fired everyday, at midnight.
     * </p>
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void removeOldPersistentTokens() {
        LocalDate now = new LocalDate();
        persistentTokenRepository.findByTokenDateBefore(now.minusMonths(1))
            .stream().forEach(token -> {
            log.debug("Deleting token {}", token.getSeries());
            User user = token.getUser();
            user.getPersistentTokens().remove(token);
            persistentTokenRepository.delete(token);
        });
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p/>
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     * </p>
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        DateTime now = new DateTime();
        List<User> users = userRepository
            .findAllByActivatedIsFalseAndCreatedDateBefore(now.minusDays(3));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getLogin());
            userRepository.delete(user);
        }
    }
}
