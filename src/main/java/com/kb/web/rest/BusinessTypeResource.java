package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.BusinessType;
import com.kb.repository.BusinessTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing BusinessType.
 */
@RestController
@RequestMapping("/api")
public class BusinessTypeResource {

    private final Logger log = LoggerFactory.getLogger(BusinessTypeResource.class);

    @Inject
    private BusinessTypeRepository businessTypeRepository;

    /**
     * POST  /businessTypes -> Create a new businessType.
     */
    @RequestMapping(value = "/businessTypes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody BusinessType businessType) throws URISyntaxException {
        log.debug("REST request to save BusinessType : {}", businessType);
        if (businessType.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new businessType cannot already have an ID").build();
        }
        businessTypeRepository.save(businessType);
        return ResponseEntity.created(new URI("/api/businessTypes/" + businessType.getId())).build();
    }

    /**
     * PUT  /businessTypes -> Updates an existing businessType.
     */
    @RequestMapping(value = "/businessTypes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody BusinessType businessType) throws URISyntaxException {
        log.debug("REST request to update BusinessType : {}", businessType);
        if (businessType.getId() == null) {
            return create(businessType);
        }
        businessTypeRepository.save(businessType);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /businessTypes -> get all the businessTypes.
     */
    @RequestMapping(value = "/businessTypes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<BusinessType> getAll() {
        log.debug("REST request to get all BusinessTypes");
        return businessTypeRepository.findAll();
    }

    /**
     * GET  /businessTypes/:id -> get the "id" businessType.
     */
    @RequestMapping(value = "/businessTypes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BusinessType> get(@PathVariable Long id) {
        log.debug("REST request to get BusinessType : {}", id);
        return Optional.ofNullable(businessTypeRepository.findOne(id))
            .map(businessType -> new ResponseEntity<>(
                businessType,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /businessTypes/:id -> delete the "id" businessType.
     */
    @RequestMapping(value = "/businessTypes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete BusinessType : {}", id);
        businessTypeRepository.delete(id);
    }
}
