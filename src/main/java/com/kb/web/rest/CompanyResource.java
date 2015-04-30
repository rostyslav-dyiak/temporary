package com.kb.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.kb.converter.user.UserConverter;
import com.kb.domain.Company;
import com.kb.domain.Contact;
import com.kb.domain.Outlet;
import com.kb.domain.Salutation;
import com.kb.domain.User;
import com.kb.repository.ContactRepository;
import com.kb.repository.OutletRepository;
import com.kb.repository.UserRepository;
import com.kb.security.SecurityUtils;
import com.kb.service.company.CompanyService;
import com.kb.web.rest.dto.SupplierInviteDTO;
import com.kb.web.rest.dto.UserCompanyDTO;
import com.kb.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Company.
 */
@RestController
@RequestMapping("/api/companies")
public class CompanyResource {

    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    @Inject
    private CompanyService companyService;

    @Inject
    private OutletRepository outletRepository;

    @Inject
    private ContactRepository contactRepository;

    @Inject
    private UserRepository userRepository;
    
    private UserConverter userConverter = new UserConverter();

    /**
     * POST  /companies -> Create a new company.
     */
    @RequestMapping(method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody final Company company) throws URISyntaxException {
        log.debug("REST request to save Company : {}", company);
        if (company.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new company cannot already have an ID").build();
        }
        companyService.save(company);
        return ResponseEntity.created(new URI("/api/companies/" + company.getId())).build();
    }

    /**
     * PUT  /companies -> Updates an existing company.
     */
    @RequestMapping(method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody final Company company) throws URISyntaxException {
        log.debug("REST request to update Company : {}", company);
        if (company.getId() == null) {
            return create(company);
        }
        companyService.save(company);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /companies -> get all the companies.
     */
    @RequestMapping(method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Company>> getAll(@RequestParam(value = "page", required = false) final Integer offset,
                                                @RequestParam(value = "per_page", required = false) final Integer limit)
        throws URISyntaxException {
        log.debug("REST request to get all Companeies : ");
        Page<Company> page = companyService.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/companies", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /companies/:id -> get the "id" company.
     */
    @RequestMapping(value = "/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Company> get(@PathVariable final Long id) {
        log.debug("REST request to get Company : {}", id);
        return Optional.ofNullable(companyService.find(id))
            .map(company -> new ResponseEntity<>(
                company,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /companies/:id -> delete the "id" company.
     */
    @RequestMapping(value = "/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable final Long id) {
        log.debug("REST request to delete Company : {}", id);
        companyService.delete(id);
    }

    @RequestMapping(value = "/{id}/outlets",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Outlet>> getOutletsForCompany(@RequestParam(value = "page" , required = false) Integer offset,
            @RequestParam(value = "per_page", required = false) Integer limit,
    		@PathVariable final Long id) throws URISyntaxException{
    	
    	log.debug("REST request to get Outlets for Company : {}", id);
    	
    	Company company = companyService.find(id);
    	Page<Outlet> page = outletRepository.findByCompany(company, PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/"+company.getId()+"/outlets", offset, limit);
        
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/contacts",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Contact>> getContactsForCompany(@RequestParam(value = "page" , required = false) Integer offset,
            @RequestParam(value = "per_page", required = false) Integer limit,
    		@PathVariable final Long id) throws URISyntaxException{
    	
    	log.debug("REST request to get Contacts for Company : {}", id);
    	
    	Company company = companyService.find(id);
    	Page<Contact> page = contactRepository.findByCompany(company, PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/"+company.getId()+"/contacts", offset, limit);
        
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/users",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<UserCompanyDTO>> getUsersForCompany(@RequestParam(value = "page" , required = false) Integer offset,
            @RequestParam(value = "per_page", required = false) Integer limit) throws URISyntaxException{
    	
    	log.debug("REST request to get Users for current Company");
    	
    	String currentLogin = SecurityUtils.getCurrentLogin();
    	Optional<User> currentUser = userRepository.findOneByEmail(currentLogin);
    	Company company = companyService.find(currentUser.get().getCompany().getId());
    	
    	Page<User> page = userRepository.findByCompany(company, PaginationUtil.generatePageRequest(offset, limit));
    	
    	List<UserCompanyDTO> convertedDtoList = userConverter.convertAll(page.getContent());
    	
    	HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/users", offset, limit);
        
        return new ResponseEntity<>(convertedDtoList, headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public ResponseEntity<UserCompanyDTO> getUserById(@RequestParam(value = "page" , required = false) Integer offset,
                @RequestParam(value = "per_page", required = false) Integer limit,
                @PathVariable final Long id) throws URISyntaxException{
        	
        	log.debug("REST request to get User by id");
        	
        	User userById = userRepository.findOne(id);
        	
        	UserCompanyDTO convertedDto = userConverter.convert(userById);
        	
        	//HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/users", offset, limit);
            
            return new ResponseEntity<UserCompanyDTO>(convertedDto, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/users",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public ResponseEntity<?> getUserById(@RequestBody final SupplierInviteDTO invitedUser) throws URISyntaxException{
        	
        	log.debug("REST request to update User");
        	
        	Optional<User> userById = userRepository.findOneByEmail(invitedUser.getEmail());
        	User userToUpdate = userById.get();
        	
        	Salutation salutation = invitedUser.getSalutation();
        	String firstName = invitedUser.getFirstName();
        	String title = invitedUser.getTitle();
        	String status = invitedUser.getStatus();
        	String email = invitedUser.getEmail(); 
            String role = invitedUser.getRole();
            String contactNumber = invitedUser.getContactNumber();

            userToUpdate.setSalutation(salutation);
            userToUpdate.setFirstName(firstName);
            userToUpdate.setTitle(title);
            userToUpdate.setStatus(status);
            userToUpdate.setEmail(email);
            userToUpdate.setContactNumber(contactNumber);
            
            userRepository.save(userToUpdate);
            
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
}
