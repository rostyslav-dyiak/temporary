package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.TaxType;
import com.kb.repository.TaxTypeRepository;
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
 * REST controller for managing TaxType.
 */
@RestController
@RequestMapping("/api")
public class TaxTypeResource {

    private final Logger log = LoggerFactory.getLogger(TaxTypeResource.class);

    @Inject
    private TaxTypeRepository taxTypeRepository;

    /**
     * POST  /taxTypes -> Create a new taxType.
     */
    @RequestMapping(value = "/taxTypes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody TaxType taxType) throws URISyntaxException {
        log.debug("REST request to save TaxType : {}", taxType);
        if (taxType.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new taxType cannot already have an ID").build();
        }
        taxTypeRepository.save(taxType);
        return ResponseEntity.created(new URI("/api/taxTypes/" + taxType.getId())).build();
    }

    /**
     * PUT  /taxTypes -> Updates an existing taxType.
     */
    @RequestMapping(value = "/taxTypes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody TaxType taxType) throws URISyntaxException {
        log.debug("REST request to update TaxType : {}", taxType);
        if (taxType.getId() == null) {
            return create(taxType);
        }
        taxTypeRepository.save(taxType);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /taxTypes -> get all the taxTypes.
     */
    @RequestMapping(value = "/taxTypes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<TaxType>> getAll(@RequestParam(value = "page", required = false) Integer offset,
                                                @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<TaxType> page = taxTypeRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/taxTypes", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /taxTypes/:id -> get the "id" taxType.
     */
    @RequestMapping(value = "/taxTypes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TaxType> get(@PathVariable Long id) {
        log.debug("REST request to get TaxType : {}", id);
        return Optional.ofNullable(taxTypeRepository.findOne(id))
            .map(taxType -> new ResponseEntity<>(
                taxType,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /taxTypes/:id -> delete the "id" taxType.
     */
    @RequestMapping(value = "/taxTypes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete TaxType : {}", id);
        taxTypeRepository.delete(id);
    }
}
