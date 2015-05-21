package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Inquiry;
import com.kb.repository.InquiryRepository;
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
 * REST controller for managing Inquiry.
 */
@RestController
@RequestMapping("/api")
public class InquiryResource {

    private final Logger log = LoggerFactory.getLogger(InquiryResource.class);

    @Inject
    private InquiryRepository inquiryRepository;

    /**
     * POST  /inquirys -> Create a new inquiry.
     */
    @RequestMapping(value = "/inquirys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Inquiry inquiry) throws URISyntaxException {
        log.debug("REST request to save Inquiry : {}", inquiry);
        if (inquiry.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new inquiry cannot already have an ID").build();
        }
        inquiryRepository.save(inquiry);
        return ResponseEntity.created(new URI("/api/inquirys/" + inquiry.getId())).build();
    }

    /**
     * PUT  /inquirys -> Updates an existing inquiry.
     */
    @RequestMapping(value = "/inquirys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Inquiry inquiry) throws URISyntaxException {
        log.debug("REST request to update Inquiry : {}", inquiry);
        if (inquiry.getId() == null) {
            return create(inquiry);
        }
        inquiryRepository.save(inquiry);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /inquirys -> get all the inquirys.
     */
    @RequestMapping(value = "/inquirys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Inquiry>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Inquiry> page = inquiryRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/inquirys", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /inquirys/:id -> get the "id" inquiry.
     */
    @RequestMapping(value = "/inquirys/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Inquiry> get(@PathVariable Long id) {
        log.debug("REST request to get Inquiry : {}", id);
        return Optional.ofNullable(inquiryRepository.findOne(id))
            .map(inquiry -> new ResponseEntity<>(
                inquiry,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /inquirys/:id -> delete the "id" inquiry.
     */
    @RequestMapping(value = "/inquirys/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Inquiry : {}", id);
        inquiryRepository.delete(id);
    }

    @RequestMapping(value = "/inquirys/last",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Inquiry>> getAllLast(@RequestParam(value = "page" , required = false) Integer offset,
                                                @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Inquiry> page = inquiryRepository.findByChildIsNull(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/inquirys/last", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
