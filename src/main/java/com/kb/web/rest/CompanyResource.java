package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Company;
import com.kb.domain.Contact;
import com.kb.domain.Outlet;
import com.kb.repository.CompanyRepository;
import com.kb.repository.ContactRepository;
import com.kb.repository.OutletRepository;
import com.kb.web.rest.util.PaginationUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Company.
 */
@RestController
@RequestMapping("/api/companies")
public class CompanyResource {

    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private OutletRepository outletRepository;
    
    @Inject
    private ContactRepository contactRepository;
    
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
        companyRepository.save(company);
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
        companyRepository.save(company);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /companies -> get all the companies.
     */
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Company>> getAll(@RequestParam(value = "page" , required = false) final Integer offset,
                                  @RequestParam(value = "per_page", required = false) final Integer limit)
        throws URISyntaxException {
        Page<Company> page = companyRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
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
        return Optional.ofNullable(companyRepository.findOne(id))
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
        companyRepository.delete(id);
    }
    
    @RequestMapping(value = "/{id}/outlets", 
    		method = RequestMethod.GET,
    		produces =MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Outlet>> getOutletsForCompany(@RequestParam(value = "page" , required = false) Integer offset,
            @RequestParam(value = "per_page", required = false) Integer limit,
    		@PathVariable final Long id) throws URISyntaxException{
    	Company company = companyRepository.findOne(id);
    	Page<Outlet> page = outletRepository.findByCompany(company, PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/"+company.getId()+"/outlets", offset, limit);
        
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}/contacts", 
    		method = RequestMethod.GET,
    		produces =MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Contact>> getContactsForCompany(@RequestParam(value = "page" , required = false) Integer offset,
            @RequestParam(value = "per_page", required = false) Integer limit,
    		@PathVariable final Long id) throws URISyntaxException{
    	Company company = companyRepository.findOne(id);
    	Page<Contact> page = contactRepository.findByCompany(company, PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/"+company.getId()+"/contacts", offset, limit);
        
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
