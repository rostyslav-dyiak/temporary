package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.SupplierDetails;
import com.kb.repository.SupplierDetailsRepository;
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
 * REST controller for managing SupplierDetails.
 */
@RestController
@RequestMapping("/api")
public class SupplierDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SupplierDetailsResource.class);

    @Inject
    private SupplierDetailsRepository supplierDetailsRepository;

    /**
     * POST  /supplierDetailss -> Create a new supplierDetails.
     */
    @RequestMapping(value = "/supplierDetails",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody SupplierDetails supplierDetails) throws URISyntaxException {
        log.debug("REST request to save SupplierDetails : {}", supplierDetails);
        if (supplierDetails.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new supplierDetails cannot already have an ID").build();
        }
        supplierDetailsRepository.save(supplierDetails);
        return ResponseEntity.created(new URI("/api/supplierDetailss/" + supplierDetails.getId())).build();
    }

    /**
     * PUT  /supplierDetailss -> Updates an existing supplierDetails.
     */
    @RequestMapping(value = "/supplierDetails",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody SupplierDetails supplierDetails) throws URISyntaxException {
        log.debug("REST request to update SupplierDetails : {}", supplierDetails);
        if (supplierDetails.getId() == null) {
            return create(supplierDetails);
        }
        supplierDetailsRepository.save(supplierDetails);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /supplierDetailss -> get all the supplierDetailss.
     */
    @RequestMapping(value = "/supplierDetails",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SupplierDetails>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<SupplierDetails> page = supplierDetailsRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/supplierDetailss", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /supplierDetailss/:id -> get the "id" supplierDetails.
     */
    @RequestMapping(value = "/supplierDetails/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SupplierDetails> get(@PathVariable Long id) {
        log.debug("REST request to get SupplierDetails : {}", id);
        return Optional.ofNullable(supplierDetailsRepository.findOne(id))
            .map(supplierDetails -> new ResponseEntity<>(
                supplierDetails,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /supplierDetailss/:id -> delete the "id" supplierDetails.
     */
    @RequestMapping(value = "/supplierDetails/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete SupplierDetails : {}", id);
        supplierDetailsRepository.delete(id);
    }
}
