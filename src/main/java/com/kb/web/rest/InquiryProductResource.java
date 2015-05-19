package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.InquiryProduct;
import com.kb.repository.InquiryProductRepository;
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
 * REST controller for managing InquiryProduct.
 */
@RestController
@RequestMapping("/api")
public class InquiryProductResource {

    private final Logger log = LoggerFactory.getLogger(InquiryProductResource.class);

    @Inject
    private InquiryProductRepository inquiryProductRepository;

    /**
     * POST  /inquiryProducts -> Create a new inquiryProduct.
     */
    @RequestMapping(value = "/inquiryProducts",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody InquiryProduct inquiryProduct) throws URISyntaxException {
        log.debug("REST request to save InquiryProduct : {}", inquiryProduct);
        if (inquiryProduct.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new inquiryProduct cannot already have an ID").build();
        }
        inquiryProductRepository.save(inquiryProduct);
        return ResponseEntity.created(new URI("/api/inquiryProducts/" + inquiryProduct.getId())).build();
    }

    /**
     * PUT  /inquiryProducts -> Updates an existing inquiryProduct.
     */
    @RequestMapping(value = "/inquiryProducts",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody InquiryProduct inquiryProduct) throws URISyntaxException {
        log.debug("REST request to update InquiryProduct : {}", inquiryProduct);
        if (inquiryProduct.getId() == null) {
            return create(inquiryProduct);
        }
        inquiryProductRepository.save(inquiryProduct);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /inquiryProducts -> get all the inquiryProducts.
     */
    @RequestMapping(value = "/inquiryProducts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<InquiryProduct> getAll() {
        log.debug("REST request to get all InquiryProducts");
        return inquiryProductRepository.findAll();
    }

    /**
     * GET  /inquiryProducts/:id -> get the "id" inquiryProduct.
     */
    @RequestMapping(value = "/inquiryProducts/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InquiryProduct> get(@PathVariable Long id) {
        log.debug("REST request to get InquiryProduct : {}", id);
        return Optional.ofNullable(inquiryProductRepository.findOne(id))
            .map(inquiryProduct -> new ResponseEntity<>(
                inquiryProduct,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /inquiryProducts/:id -> delete the "id" inquiryProduct.
     */
    @RequestMapping(value = "/inquiryProducts/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete InquiryProduct : {}", id);
        inquiryProductRepository.delete(id);
    }
}
