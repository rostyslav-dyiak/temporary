package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.InquiryOutlet;
import com.kb.repository.InquiryOutletRepository;
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
 * REST controller for managing InquiryOutlet.
 */
@RestController
@RequestMapping("/api")
public class InquiryOutletResource {

    private final Logger log = LoggerFactory.getLogger(InquiryOutletResource.class);

    @Inject
    private InquiryOutletRepository inquiryOutletRepository;

    /**
     * POST  /inquiryOutlets -> Create a new inquiryOutlet.
     */
    @RequestMapping(value = "/inquiryOutlets",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody InquiryOutlet inquiryOutlet) throws URISyntaxException {
        log.debug("REST request to save InquiryOutlet : {}", inquiryOutlet);
        if (inquiryOutlet.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new inquiryOutlet cannot already have an ID").build();
        }
        inquiryOutletRepository.save(inquiryOutlet);
        return ResponseEntity.created(new URI("/api/inquiryOutlets/" + inquiryOutlet.getId())).build();
    }

    /**
     * PUT  /inquiryOutlets -> Updates an existing inquiryOutlet.
     */
    @RequestMapping(value = "/inquiryOutlets",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody InquiryOutlet inquiryOutlet) throws URISyntaxException {
        log.debug("REST request to update InquiryOutlet : {}", inquiryOutlet);
        if (inquiryOutlet.getId() == null) {
            return create(inquiryOutlet);
        }
        inquiryOutletRepository.save(inquiryOutlet);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /inquiryOutlets -> get all the inquiryOutlets.
     */
    @RequestMapping(value = "/inquiryOutlets",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<InquiryOutlet> getAll() {
        log.debug("REST request to get all InquiryOutlets");
        return inquiryOutletRepository.findAll();
    }

    /**
     * GET  /inquiryOutlets/:id -> get the "id" inquiryOutlet.
     */
    @RequestMapping(value = "/inquiryOutlets/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InquiryOutlet> get(@PathVariable Long id) {
        log.debug("REST request to get InquiryOutlet : {}", id);
        return Optional.ofNullable(inquiryOutletRepository.findOne(id))
            .map(inquiryOutlet -> new ResponseEntity<>(
                inquiryOutlet,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /inquiryOutlets/:id -> delete the "id" inquiryOutlet.
     */
    @RequestMapping(value = "/inquiryOutlets/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete InquiryOutlet : {}", id);
        inquiryOutletRepository.delete(id);
    }
}
