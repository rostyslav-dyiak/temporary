package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.*;
import com.kb.repository.PersistentTokenRepository;
import com.kb.repository.UserRepository;
import com.kb.security.SecurityUtils;
import com.kb.service.MailService;
import com.kb.service.UserService;
import com.kb.service.company.CompanyService;
import com.kb.web.rest.dto.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    @Inject
    private PersistentTokenRepository persistentTokenRepository;

    @Inject
    private MailService mailService;

    @Inject
    private CompanyService companyService;

    /**
     * POST  /register -> register the user.
     */
    @RequestMapping(value = "/register",
        method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.TEXT_PLAIN_VALUE)
    @Timed
    public ResponseEntity<?> registerAccount(@Valid @RequestBody final UserCompanyDTO userCompanyDTO, final HttpServletRequest request) {
        UserDTO userDTO = userCompanyDTO.getUserDTO();
        String role = "";
        return userRepository.findOneByEmail(userDTO.getEmail())
            .map(user -> new ResponseEntity<>("e-mail address already in use", HttpStatus.BAD_REQUEST))
            .orElseGet(() -> {
                Company company = userCompanyDTO.getCompany();
                companyService.save(company);
                User user = userService.createUserInformation(userDTO.getEmail().toLowerCase(), userDTO.getPassword(),
                    userDTO.getFirstName(), userDTO.getLastName(), userDTO.getLangKey(), company, role);
                String baseUrl = MessageFormat.format("{0}://{1}:{2}", request.getServerName(), request.getScheme(), Integer.toString(request.getServerPort()));

                mailService.sendActivationEmail(user, baseUrl);
                return new ResponseEntity<>(HttpStatus.CREATED);
            });
    }

    @RequestMapping(value = "/invite",
        method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.TEXT_PLAIN_VALUE)
    @Timed
    public ResponseEntity<?> registerCompanyAndInvite(@Valid @RequestBody final CompanyUserInviteDTO userCompanyDTO, final HttpServletRequest request) {
        String email = userCompanyDTO.getEmail();
        String role = userCompanyDTO.getRole();
        Company company = userCompanyDTO.getCompany();
        return userRepository.findOneByEmail(email)
            .map(user -> new ResponseEntity<>("e-mail address already in use", HttpStatus.BAD_REQUEST))
            .orElseGet(() -> {
                companyService.save(company);
                User user = userService.createInitialUserInformation(email, company, role);
                String baseUrl = MessageFormat.format("{0}://{1}:{2}/{3}", request.getScheme(), request.getServerName(), Integer.toString(request.getServerPort()), "#/app/sign_up");
                mailService.sendActivationEmail(user, baseUrl);
                return new ResponseEntity<>(HttpStatus.CREATED);
            });
    }

    @RequestMapping(value = "/invite/update",
        method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.TEXT_PLAIN_VALUE)
    @Timed
    public ResponseEntity<?> updateUserInvite(@Valid @RequestBody final CompanyUserInviteDTO userCompanyDTO, final HttpServletRequest request) {
        String oldEmail = userCompanyDTO.getEmail();
        Company company = userCompanyDTO.getCompany();
        return userRepository.findOneByEmail(oldEmail)
            .map(u -> {
                if (company.getStatus().equals(CompanyStatus.PENDING)) {
                    User user = userService.changeUserEmail(oldEmail, company.getEmail());
                    String baseUrl = MessageFormat.format("{0}://{1}:{2}/{3}", request.getScheme(), request.getServerName(), Integer.toString(request.getServerPort()), "#/app/sign_up");
                    mailService.sendActivationEmail(user, baseUrl);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }
                return new ResponseEntity<>("Company status isn't pending.", HttpStatus.BAD_REQUEST);
            })
            .orElseGet(() -> new ResponseEntity<>("e-mail address not found", HttpStatus.BAD_REQUEST));
    }

    /**
     * GET  /activate -> activate the registered user.
     */
    @RequestMapping(value = "/activate",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<User> activateAccount(@RequestParam(value = "key") final String key) {
        User user = userService.getUserByRegistrationKey(key);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * GET  /authenticate -> check if the user is authenticated, and return its login.
     */
    @RequestMapping(value = "/authenticate",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public String isAuthenticated(final HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }
//TODO: Changed User for only one Authority

    /**
     * GET  /account -> get the current user.
     */
    @RequestMapping(value = "/account",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserDTO> getAccount() {

        return Optional.ofNullable(userService.getUserWithAuthorities())
            .map(user -> new ResponseEntity<>(
                new UserDTO(
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
                ),
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * POST  /account -> update the current user information.
     */
    @RequestMapping(value = "/account",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<String> saveAccount(@RequestBody final UserDTO userDTO) {
        return userRepository
            .findOneByLogin(userDTO.getEmail().toLowerCase())
            .filter(u -> u.getLogin().equals(SecurityUtils.getCurrentLogin())) // Restricting
            .map(u -> {
                userService.updateUserInformation(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
                return new ResponseEntity<String>(HttpStatus.OK);
            })
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/invite_supplier_member",
        method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.TEXT_PLAIN_VALUE)
    @Timed
    public ResponseEntity<?> registerCompanyAndInvite(@RequestBody final SupplierInviteDTO supplierInviteDTO, final HttpServletRequest request) {
        Salutation salutation = supplierInviteDTO.getSalutation();
        String firstName = supplierInviteDTO.getFirstName();
        String title = supplierInviteDTO.getTitle();
        String status = supplierInviteDTO.getStatus();
        String email = supplierInviteDTO.getEmail();
        String role = supplierInviteDTO.getRole();
        String contactNumber = supplierInviteDTO.getContactNumber();
        Company company = supplierInviteDTO.getCompany();
        Outlet outlet = supplierInviteDTO.getOutlet();
        return userRepository.findOneByEmail(email)
            .map(user -> new ResponseEntity<>("e-mail address already in use", HttpStatus.BAD_REQUEST))
            .orElseGet(() -> {
                User user = userService.createUserInformation(salutation, firstName, title, status, email, role, contactNumber, company, outlet);

                user.setOutletForMember(outlet);
                String baseUrl = MessageFormat.format("{0}://{1}:{2}/{3}", request.getScheme(), request.getServerName(), Integer.toString(request.getServerPort()), "#/app/sign_up");
                mailService.sendActivationEmail(user, baseUrl);
                return new ResponseEntity<>(HttpStatus.CREATED);
            });
    }

    /**
     * POST  /account -> update the current user information.
     */
    @RequestMapping(value = "/account/password",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserDTO> changeAccountPasswordAndDetails(@RequestBody final UserDTO userDTO) {
        return userRepository
            .findOneByEmail(userDTO.getEmail().toLowerCase())
            .map(u -> {
                UserDTO result = userService.updateUserInformation(userDTO, u.getCompany().getId());
                return new ResponseEntity<>(result, HttpStatus.OK);
            })
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * POST  /change_password -> changes the current user's password
     */
    @RequestMapping(value = "/account/change_password",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> changePassword(@RequestBody final PasswordDTO passwordDTO) {
        if (userService.checkUserPassword(passwordDTO.getOldPassword())) {
            String newPassword = passwordDTO.getNewPassword();
            if (StringUtils.isEmpty(newPassword) || newPassword.length() < 5 || newPassword.length() > 50) {
                return new ResponseEntity<>("Invalid new password for user can't be < 5 or > 50 symbols", HttpStatus.BAD_REQUEST);
            }
            userService.changePassword(newPassword);
        } else {
            return new ResponseEntity<>("Invalid password for user", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * GET  /account/sessions -> get the current open sessions.
     */
    @RequestMapping(value = "/account/sessions",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PersistentToken>> getCurrentSessions() {
        return userRepository.findOneByLogin(SecurityUtils.getCurrentLogin())
            .map(user -> new ResponseEntity<>(
                persistentTokenRepository.findByUser(user),
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * DELETE  /account/sessions?series={series} -> invalidate an existing session.
     * <p/>
     * - You can only delete your own sessions, not any other user's session
     * - If you delete one of your existing sessions, and that you are currently logged in on that session, you will
     * still be able to use that session, until you quit your browser: it does not work in real time (there is
     * no API for that), it only removes the "remember me" cookie
     * - This is also true if you invalidate your current session: you will still be able to use it until you close
     * your browser or that the session times out. But automatic login (the "remember me" cookie) will not work
     * anymore.
     * There is an API to invalidate the current session, but there is no API to check which session uses which
     * cookie.
     */
    @RequestMapping(value = "/account/sessions/{series}",
        method = RequestMethod.DELETE)
    @Timed
    public void invalidateSession(@PathVariable final String series) throws UnsupportedEncodingException {
        String decodedSeries = URLDecoder.decode(series, "UTF-8");
        userRepository.findOneByLogin(SecurityUtils.getCurrentLogin()).ifPresent(u -> {
            persistentTokenRepository.findByUser(u).stream()
                .filter(persistentToken -> StringUtils.equals(persistentToken.getSeries(), decodedSeries))
                .findAny().ifPresent(t -> persistentTokenRepository.delete(decodedSeries));
        });
    }


}
