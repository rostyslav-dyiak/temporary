package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.EateryDetails;
import com.kb.repository.EateryDetailsRepository;
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
 * REST controller for managing EateryDetails.
 */
@RestController
@RequestMapping("/api")
public class EateryDetailsResource {

    private final Logger log = LoggerFactory.getLogger(EateryDetailsResource.class);

    @Inject
    private EateryDetailsRepository eateryDetailsRepository;

    /**
     * POST  /eateryDetailss -> Create a new eateryDetails.
     */
    @RequestMapping(value = "/eateryDetails",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody EateryDetails eateryDetails) throws URISyntaxException {
        log.debug("REST request to save EateryDetails : {}", eateryDetails);
        if (eateryDetails.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new eateryDetails cannot already have an ID").build();
        }
        eateryDetailsRepository.save(eateryDetails);
        return ResponseEntity.created(new URI("/api/eatery/details" + eateryDetails.getId())).build();
    }

    /**
     * PUT  /eateryDetailss -> Updates an existing eateryDetails.
     */
    @RequestMapping(value = "/eateryDetails",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody EateryDetails eateryDetails) throws URISyntaxException {
        log.debug("REST request to update EateryDetails : {}", eateryDetails);
        if (eateryDetails.getId() == null) {
            return create(eateryDetails);
        }
        eateryDetailsRepository.save(eateryDetails);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /eateryDetailss -> get all the eateryDetailss.
     */
    @RequestMapping(value = "/eateryDetails",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<EateryDetails>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<EateryDetails> page = eateryDetailsRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/eatery/details", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /eateryDetailss/:id -> get the "id" eateryDetails.
     */
    @RequestMapping(value = "/{id}/eateryDetails",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EateryDetails> get(@PathVariable Long id) {
        log.debug("REST request to get EateryDetails : {}", id);
        return Optional.ofNullable(eateryDetailsRepository.findOne(id))
            .map(eateryDetails -> new ResponseEntity<>(
                eateryDetails,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /eateryDetailss/:id -> delete the "id" eateryDetails.
     */
    @RequestMapping(value = "/{id}/eateryDetails",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete EateryDetails : {}", id);
        eateryDetailsRepository.delete(id);
    }
}
