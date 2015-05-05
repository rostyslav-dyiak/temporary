package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.PaymentTerm;
import com.kb.repository.PaymentTermRepository;
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
 * REST controller for managing PaymentTerm.
 */
@RestController
@RequestMapping("/api")
public class PaymentTermResource {

    private final Logger log = LoggerFactory.getLogger(PaymentTermResource.class);

    @Inject
    private PaymentTermRepository paymentTermRepository;

    /**
     * POST  /paymentTerms -> Create a new paymentTerm.
     */
    @RequestMapping(value = "/paymentTerms",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody PaymentTerm paymentTerm) throws URISyntaxException {
        log.debug("REST request to save PaymentTerm : {}", paymentTerm);
        if (paymentTerm.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new paymentTerm cannot already have an ID").build();
        }
        paymentTermRepository.save(paymentTerm);
        return ResponseEntity.created(new URI("/api/paymentTerms/" + paymentTerm.getId())).build();
    }

    /**
     * PUT  /paymentTerms -> Updates an existing paymentTerm.
     */
    @RequestMapping(value = "/paymentTerms",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody PaymentTerm paymentTerm) throws URISyntaxException {
        log.debug("REST request to update PaymentTerm : {}", paymentTerm);
        if (paymentTerm.getId() == null) {
            return create(paymentTerm);
        }
        paymentTermRepository.save(paymentTerm);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /paymentTerms -> get all the paymentTerms.
     */
    @RequestMapping(value = "/paymentTerms",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PaymentTerm>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<PaymentTerm> page = paymentTermRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/paymentTerms", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /paymentTerms/:id -> get the "id" paymentTerm.
     */
    @RequestMapping(value = "/paymentTerms/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaymentTerm> get(@PathVariable Long id) {
        log.debug("REST request to get PaymentTerm : {}", id);
        return Optional.ofNullable(paymentTermRepository.findOne(id))
            .map(paymentTerm -> new ResponseEntity<>(
                paymentTerm,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /paymentTerms/:id -> delete the "id" paymentTerm.
     */
    @RequestMapping(value = "/paymentTerms/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete PaymentTerm : {}", id);
        paymentTermRepository.delete(id);
    }
}
